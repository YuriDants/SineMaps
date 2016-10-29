package br.edu.ifpb.appbuscarempregos;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import br.edu.ifpb.appbuscarempregos.listeners.ListarBrasilOnClickListener;
import br.edu.ifpb.appbuscarempregos.listeners.ListarCGOnClickListener;

public class MainActivity extends Activity {
    private Button bBrasil;
    private Button bCG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bBrasil = (Button) findViewById(R.id.bBrasil);
        bCG = (Button) findViewById(R.id.bCG);

        bBrasil.setOnClickListener(new ListarBrasilOnClickListener(this));
        bCG.setOnClickListener(new ListarCGOnClickListener(this));
    }
}
