package br.edu.ifpb.appbuscarempregos.listeners;

import android.content.Intent;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

import br.edu.ifpb.appbuscarempregos.Sine;
import br.edu.ifpb.appbuscarempregos.activity.ListarCGActivity;
import br.edu.ifpb.appbuscarempregos.activity.ListarGPSActivity;
import br.edu.ifpb.appbuscarempregos.activity.MainActivity;
import br.edu.ifpb.appbuscarempregos.activity.MapsActivity;

/**
 * Created by Henrique on 11/11/2016.
 */

public class MapsOnClickListener implements View.OnClickListener {
    private ListarCGActivity listarCGActivity = null;
    private ListarGPSActivity listarGPSActivity = null;

    public MapsOnClickListener(ListarCGActivity main) {
        this.listarCGActivity = main;
    }

    public MapsOnClickListener(ListarGPSActivity main) {
        this.listarGPSActivity = main;
    }

    @Override
    public void onClick(View view) {
        if (listarCGActivity != null) {
            Intent intent = new Intent(listarCGActivity, MapsActivity.class);
            intent.putExtra("lista", (ArrayList<Sine>) listarCGActivity.getListaBase());
            listarCGActivity.startActivity(intent);
        } else {
            Intent intent = new Intent(listarGPSActivity, MapsActivity.class);
            intent.putExtra("lista", (ArrayList<Sine>) listarGPSActivity.getListaBase());
            listarGPSActivity.startActivity(intent);
        }
    }
}
