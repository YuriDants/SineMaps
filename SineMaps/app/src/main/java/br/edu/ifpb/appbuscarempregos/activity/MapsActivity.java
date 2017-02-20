package br.edu.ifpb.appbuscarempregos.activity;

import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.appbuscarempregos.R;
import br.edu.ifpb.appbuscarempregos.Sine;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        List<Sine> sines = (ArrayList<Sine>) getIntent().getSerializableExtra("lista");

        LatLng CG = new LatLng(getIntent().getDoubleExtra("lat", -7.219204), getIntent().getDoubleExtra("long", -35.882901));
        mMap.addMarker(new MarkerOptions()
                .position(CG)
                .title("Ponto de referência")
                .snippet("Onde Você está")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_sine_red)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(CG));

        for (int i = 0; i < sines.size(); i++) {
            LatLng ponto = new LatLng(Double.valueOf(sines.get(i).getLat()), Double.valueOf(sines.get(i).getLongi()));
            MarkerOptions options = new MarkerOptions()
                    .position(ponto)
                    .title("Nome: " + sines.get(i).getNome())
                    .snippet("Telefone: " + sines.get(i).getTelefone())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_sine_blue));

            mMap.addMarker(options);
            mMap.setMinZoomPreference(8);
        }
    }
}
