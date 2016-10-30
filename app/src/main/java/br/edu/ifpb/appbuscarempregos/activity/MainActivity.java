package br.edu.ifpb.appbuscarempregos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.io.Serializable;

import br.edu.ifpb.appbuscarempregos.R;
import br.edu.ifpb.appbuscarempregos.listeners.GpsOnClickListener;
import br.edu.ifpb.appbuscarempregos.listeners.ListarBrasilOnClickListener;
import br.edu.ifpb.appbuscarempregos.listeners.ListarCGOnClickListener;

public class MainActivity extends Activity{
    private Button bBrasil;
    private Button bCG;
    private Button bGps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bBrasil = (Button) findViewById(R.id.bBrasil);
        bCG = (Button) findViewById(R.id.bCG);
        bGps = (Button) findViewById(R.id.bGps);

        bBrasil.setOnClickListener(new ListarBrasilOnClickListener(this));
        bCG.setOnClickListener(new ListarCGOnClickListener(this));
        bGps.setOnClickListener(new GpsOnClickListener(this));

    }

    public Button getbBrasil() {
        return bBrasil;
    }

    public void setbBrasil(Button bBrasil) {
        this.bBrasil = bBrasil;
    }

    public Button getbCG() {
        return bCG;
    }

    public void setbCG(Button bCG) {
        this.bCG = bCG;
    }

    public Button getbGps() {
        return bGps;
    }

    public void setbGps(Button bGps) {
        this.bGps = bGps;
    }

}
