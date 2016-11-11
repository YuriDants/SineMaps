package br.edu.ifpb.appbuscarempregos.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
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
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        List<Sine> sines = (ArrayList<Sine>) getIntent().getSerializableExtra("lista");

        LatLng CG = new LatLng(-7.219204, -35.882901);
        mMap.addMarker(new MarkerOptions().position(CG).title("Ponto de referência"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(CG));

        for (int i = 0; i < sines.size(); i++) {
            LatLng ponto = new LatLng(Double.valueOf(sines.get(i).getLat()), Double.valueOf(sines.get(i).getLongi()));
            mMap.addMarker(new MarkerOptions().position(ponto).title(sines.get(i).getNome()));
            Log.e("aaaaaaaaaaaa", "acabou: " + i);
        }
    }
}
