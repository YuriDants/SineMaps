package br.edu.ifpb.appbuscarempregos.listeners;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import br.edu.ifpb.appbuscarempregos.activity.ListarBrasilActivity;
import br.edu.ifpb.appbuscarempregos.activity.MainActivity;

/**
 * Created by Henrique on 30/10/2016.
 */

public class GpsLocationListener implements LocationListener {
    private MainActivity main;

    public GpsLocationListener(MainActivity main) {
        this.main = main;
    }

    @Override
    public void onLocationChanged(Location location) {
        Intent intent = new Intent(main, ListarBrasilActivity.class);

        intent.putExtra("lat", location.getLatitude());
        intent.putExtra("long", location.getLongitude());

        main.startActivity(intent);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
