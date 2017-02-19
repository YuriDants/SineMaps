package br.edu.ifpb.appbuscarempregos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import br.edu.ifpb.appbuscarempregos.R;
import br.edu.ifpb.appbuscarempregos.listeners.ListarBrasilOnClickListener;
import br.edu.ifpb.appbuscarempregos.listeners.ListarCGOnClickListener;
import br.edu.ifpb.appbuscarempregos.listeners.ListarGPSOnClickListener;

public class MainActivity extends Activity {
    private Button bBrasil;
    private Button bCG;
    private Button bGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bBrasil = (Button) findViewById(R.id.bBrasil);
        bCG = (Button) findViewById(R.id.bCG);
        bGPS = (Button) findViewById(R.id.bGPS);

        bBrasil.setOnClickListener(new ListarBrasilOnClickListener(this));
        bCG.setOnClickListener(new ListarCGOnClickListener(this));
        bGPS.setOnClickListener(new ListarGPSOnClickListener(this));
    }
}
