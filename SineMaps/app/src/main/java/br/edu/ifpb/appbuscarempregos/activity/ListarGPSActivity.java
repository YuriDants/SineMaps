package br.edu.ifpb.appbuscarempregos.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.edu.ifpb.appbuscarempregos.R;
import br.edu.ifpb.appbuscarempregos.Sine;
import br.edu.ifpb.appbuscarempregos.asynctask.HttpGetAsyncTask;
import br.edu.ifpb.appbuscarempregos.asynctask.HttpGetSineSimplesAsyncTask;
import br.edu.ifpb.appbuscarempregos.listeners.DetalharOnItemClickListener;
import br.edu.ifpb.appbuscarempregos.listeners.MapsOnClickListener;
import br.edu.ifpb.appbuscarempregos.listeners.PesquisarOnTextWatcherListener;

public class ListarGPSActivity extends Activity implements LocationListener {
    private ArrayAdapter<Sine> adapter = null;
    private List<Sine> listaBase;
    private ListView list;

    private Button mapSines;

    private LocationManager locationManager;
    private Location location;

    private final int REQUEST_LOCATION = 200;

    private HttpGetAsyncTask httpGetAsyncTask;

    private String latitude;
    private String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cg);

        list = (ListView) findViewById(R.id.list);
        httpGetAsyncTask = new HttpGetnAsyncTask();

        EditText searchText = (EditText) findViewById(R.id.SearchText);
        searchText.addTextChangedListener(new PesquisarOnTextWatcherListener(this));

        list.setOnItemClickListener(new DetalharOnItemClickListener(this));

        mapSines = (Button) findViewById(R.id.mapSines);
        mapSines.setOnClickListener(new MapsOnClickListener(this));

        init();
    }

    public ArrayAdapter<Sine> getAdapter() {
        return adapter;
    }

    public void setAdapter(ArrayAdapter<Sine> adapter) {
        this.adapter = adapter;
    }

    public List<Sine> getListaBase() {
        return listaBase;
    }

    public void setListaBase(List<Sine> listaBase) {
        this.listaBase = listaBase;
    }

    public ListView getList() {
        return list;
    }

    public void setList(List<Sine> lista) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        list.setAdapter(adapter);
    }

    public void init() {

        // Inicializar comunicação com o módulo de GPS do dispositivo móvel.
        locationManager = (LocationManager) this.getSystemService(Service.LOCATION_SERVICE);

        //Verificar permissões de acesso.
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Requisitar permissão de acesso ao usuário.
            ActivityCompat.requestPermissions(this,
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
                latitude = String.valueOf(location.getLatitude());
                longitude = String.valueOf(location.getLongitude());

                //verificação
                Log.i("latitudeeeeeeeeeeeeeee", latitude);
                Log.i("longitudeeeeeeeeeeeeee", longitude);

                buscar(latitude, longitude);
            }

        } else showGPSDisabledAlertToUser();
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = String.valueOf(location.getLatitude());
        longitude = String.valueOf(location.getLongitude());

        //buscar(latitude, longitude);
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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("habilite o GPS")
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

    private void buscar(String latitude, String longitude) {
        try {
            listaBase = httpGetAsyncTask.execute("http://mobile-aceite.tcu.gov.br/mapa-da-saude/rest/emprego/latitude/" + latitude +
                    "/longitude/" + longitude + "/raio/100").get();
            setList(listaBase);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
