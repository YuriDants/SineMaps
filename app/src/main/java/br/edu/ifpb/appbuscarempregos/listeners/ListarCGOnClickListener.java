package br.edu.ifpb.appbuscarempregos.listeners;

import android.content.Intent;
import android.view.View;

import br.edu.ifpb.appbuscarempregos.activity.MainActivity;
import br.edu.ifpb.appbuscarempregos.activity.ListarCGActivity;

/**
 * Created by Henrique on 29/10/2016.
 */

public class ListarCGOnClickListener implements View.OnClickListener {
    private MainActivity main;

    public ListarCGOnClickListener(MainActivity main) {
        this.main = main;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(main, ListarCGActivity.class);
        intent.putExtra("lat",-7.242662);
        intent.putExtra("long",-35.9716057);

        main.startActivity(intent);

    }
}
