package br.edu.ifpb.appbuscarempregos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button bBrasil;
    private Button bCG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bBrasil.setOnClickListener(null);
        bCG.setOnClickListener(null);
    }
}
