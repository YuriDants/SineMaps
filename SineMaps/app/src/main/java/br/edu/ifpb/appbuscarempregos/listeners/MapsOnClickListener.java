package br.edu.ifpb.appbuscarempregos.listeners;

import android.content.Intent;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

import br.edu.ifpb.appbuscarempregos.Sine;
import br.edu.ifpb.appbuscarempregos.activity.ListarCGActivity;
import br.edu.ifpb.appbuscarempregos.activity.MainActivity;
import br.edu.ifpb.appbuscarempregos.activity.MapsActivity;

/**
 * Created by Henrique on 11/11/2016.
 */

public class MapsOnClickListener implements View.OnClickListener {
    private ListarCGActivity main;

    public MapsOnClickListener(ListarCGActivity main) {
        this.main = main;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(main, MapsActivity.class);
        intent.putExtra("lista", (ArrayList<Sine>) main.getListaBase());
        main.startActivity(intent);
    }
}
