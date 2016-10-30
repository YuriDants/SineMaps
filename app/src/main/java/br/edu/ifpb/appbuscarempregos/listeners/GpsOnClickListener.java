package br.edu.ifpb.appbuscarempregos.listeners;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import br.edu.ifpb.appbuscarempregos.activity.ListarBrasilActivity;
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
        LocationManager locationManager = (LocationManager) main.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(main, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(main, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, new GpsLocationListener(main), null);

    }
}
