package br.edu.ifpb.appbuscarempregos.listeners;

import android.content.Intent;
import android.view.View;

import br.edu.ifpb.appbuscarempregos.MainActivity;
import br.edu.ifpb.appbuscarempregos.activity.ListarBrasilActivity;

/**
 * Created by Henrique on 29/10/2016.
 */

public class ListarBrasilOnClickListener implements View.OnClickListener {
    private MainActivity main;

    public ListarBrasilOnClickListener(MainActivity main) {
        this.main = main;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(main, ListarBrasilActivity.class);
        main.startActivity(intent);
    }
}
