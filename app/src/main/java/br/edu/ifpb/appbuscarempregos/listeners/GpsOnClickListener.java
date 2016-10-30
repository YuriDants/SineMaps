package br.edu.ifpb.appbuscarempregos.listeners;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;

import br.edu.ifpb.appbuscarempregos.activity.ListarBrasilActivity;
import br.edu.ifpb.appbuscarempregos.activity.ListarCGActivity;
import br.edu.ifpb.appbuscarempregos.activity.MainActivity;

/**
 * Created by Henrique on 30/10/2016.
 */

public class GpsOnClickListener implements View.OnClickListener {
    private MainActivity main;

    public GpsOnClickListener(MainActivity main) {
        this.main = main;
    }

    @Override
    public void onClick(View view) {
        Log.i("OnClickListener", "começo");
        LocationManager locationManager = (LocationManager) main.getSystemService(Context.LOCATION_SERVICE);
        Log.i("OnClickListener", "location manager");
        if (ActivityCompat.checkSelfPermission(main, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(main, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, new GpsLocationListener(main), null);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Log.i("OnClickListener",""+location.getLatitude());
        Log.i("OnClickListener",""+location.getLongitude());
        Log.i("OnClickListener", "location");

        Intent intent = new Intent(main, ListarCGActivity.class);
        Log.i("OnClickListener", "intent");
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(isGPSEnabled) {

            intent.putExtra("lat", location.getLatitude());
            intent.putExtra("long", location.getLongitude());

            Log.i("OnClickListener", "putextra");

            main.startActivity(intent);
        }
    }
}
