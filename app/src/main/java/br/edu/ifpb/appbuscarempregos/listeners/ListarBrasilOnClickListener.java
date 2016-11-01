package br.edu.ifpb.appbuscarempregos.listeners;

import android.content.Intent;
import android.view.View;

import br.edu.ifpb.appbuscarempregos.activity.MainActivity;
import br.edu.ifpb.appbuscarempregos.activity.ListarBrasilActivity;
import br.edu.ifpb.appbuscarempregos.asynctask.HttpGetAsyncTask;

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
        HttpGetAsyncTask httpGetAsyncTask = new HttpGetAsyncTask(main, 1);
        httpGetAsyncTask.execute("http://mobile-aceite.tcu.gov.br/mapa-da-saude/rest/emprego?quantidade=10000");
    }
}
