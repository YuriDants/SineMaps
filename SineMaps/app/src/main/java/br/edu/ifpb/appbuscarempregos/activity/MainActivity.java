package br.edu.ifpb.appbuscarempregos.activity;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import br.edu.ifpb.appbuscarempregos.Manifest;
import br.edu.ifpb.appbuscarempregos.R;
import br.edu.ifpb.appbuscarempregos.listeners.ListarBrasilOnClickListener;
import br.edu.ifpb.appbuscarempregos.listeners.ListarCGOnClickListener;

public class MainActivity extends Activity implements LocationListener{
    private Button bBrasil;
    private Button bCG;

    //passar pra a classe de listener
    private LocationManager locationManager;
    private Location location;
    //.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bBrasil = (Button) findViewById(R.id.bBrasil);
        bCG = (Button) findViewById(R.id.bCG);

        //bBrasil.setOnClickListener(new ListarBrasilOnClickListener(this));
        bCG.setOnClickListener(new ListarCGOnClickListener(this));
    }

    //substituir o onClick pelo listener e copiar todo o código daqui pra baixo lá.
    public void onClickGPS(View view){

        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);

        int permissionCheck1 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionCheck2 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (permissionCheck1 != PackageManager.PERMISSION_GRANTED && permissionCheck2 != PackageManager.PERMISSION_GRANTED){
            String[] strings = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(MainActivity.this, strings, REQUEST_LOCATION);

        } else {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 2, (LocationListener) this);

            if (locationManager != null){
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

        }

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){

            if (location != null){
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();


                Intent intent = new Intent (this, MapsActivity.class); //só coloquei a mapsacrivity, não tratei nada lá.
                intent.putExtra("latitude", Long.toString((long) latitude));
                intent.putExtra("longitude", Long.toString((long) longitude));
                startService(intent);

            }
        } else {
            showGPSDisabledAlertToUser();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        getAdressFromLocation(location, getApplicationContext(), new GeoCoderHandler());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

        if (provider.equals(LocationManager.GPS_PROVIDER)){
            showGPSDisabledAlertToUser();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if (requestCode == REQUEST_LOCATION){
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            }
        }
    }

    private void showGPSDisabledAlertToUser() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("O GPS está desabilitado no seu dispositivo. Deseja habilitar?")
                .setCancelable(false)
                .setPositiveButton("Direcione para as configurações para habilitar o GPS.", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(callGPSSettingIntent);
                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public static void getAddressFromLocation(final Location location,
                                              final Context context,
                                              final Handler handler) {

        // Conexão assíncrona para recuperar o Logradouro na internet.
        Thread thread = new Thread() {

            @Override
            public void run() {

                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null;

                try {

                    List<Address> list = geocoder.getFromLocation(
                            location.getLatitude(), location.getLongitude(), 1);

                    if (list != null && list.size() > 0) {
                        Address address = list.get(0);

                        // Resposta com a primeira linha de endereço e localidade.
                        result = address.getAddressLine(0) + ", " + address.getLocality() + ", " + address.getCountryName();
                    }

                } catch (IOException e) {

                    Log.e("MainActivity", "Impossível conectar ao Geocoder", e);

                } finally {

                    Message msg = Message.obtain();
                    msg.setTarget(handler);

                    if (result != null) {

                        msg.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putString("address", result);
                        msg.setData(bundle);

                    } else {
                        msg.what = 0;
                    }

                    msg.sendToTarget();
                }
            }
        };

        thread.start();
    }

    private class GeoCoderHandler extends Handler {

        @Override
        public void handleMessage(Message message) {

            String result;

            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    result = bundle.getString("address");
                    break;
                default:
                    result = null;
            }
            //currentCity.setText(result);
        }
    }


}
