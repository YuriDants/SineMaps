package br.edu.ifpb.appbuscarempregos.listeners;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import br.edu.ifpb.appbuscarempregos.activity.ListarCGActivity;
import br.edu.ifpb.appbuscarempregos.activity.ListarGPSActivity;
import br.edu.ifpb.appbuscarempregos.activity.MainActivity;

/**
 * Created by henri on 19/02/2017.
 */

public class ListarGPSOnClickListener implements View.OnClickListener, LocationListener {
    private MainActivity main;

    // Location
    private LocationManager locationManager;
    private Location location;

    // Configuração
    private final int REQUEST_LOCATION = 200;
    private static final String TAG = "MainActivity";

    private String latitude;
    private String longitude;

    public ListarGPSOnClickListener(MainActivity main) {
        this.main = main;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(main, ListarGPSActivity.class);
        main.startActivity(intent);
    }

    public void init(){

        // Inicializar comunicação com o módulo de GPS do dispositivo móvel.
        locationManager = (LocationManager) main.getSystemService(Service.LOCATION_SERVICE);

        //Verificar permissões de acesso.
        if (ActivityCompat.checkSelfPermission(main,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(main,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Requisitar permissão de acesso ao usuário.
            ActivityCompat.requestPermissions(main,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);

        } else {
            // Caso a permissão já conscentida atualizar a localização.
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 2, this);

            if (locationManager != null) {
                // Localização atualizada.
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
        }

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            if (location != null) {

                // Posições do GPS
                latitude =  String.valueOf(location.getLatitude());
                longitude =  String.valueOf(location.getLongitude());

                //verificação
                Log.i("latitudeeeeeeeeeeeeeee", latitude);
                Log.i("longitudeeeeeeeeeeeeee", longitude);
            }

        } else showGPSDisabledAlertToUser();
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude =  String.valueOf(location.getLatitude());
        longitude =  String.valueOf(location.getLongitude());

        //verificação
        Log.i("onLocationChanged lat", latitude);
        Log.i("onLocationChanged long", longitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        if (provider.equals(LocationManager.GPS_PROVIDER)) {
            showGPSDisabledAlertToUser();
        }
    }

    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(main);
        alertDialogBuilder.setMessage("Para funcionar, precisa ser habilitado o GPS")
                .setCancelable(false)
                .setPositiveButton("Direcione para as configurações para habilitar o GPS.", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        main.startActivity(callGPSSettingIntent);
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


}
