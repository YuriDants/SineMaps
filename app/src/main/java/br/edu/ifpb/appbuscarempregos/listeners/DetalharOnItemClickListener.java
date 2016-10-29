package br.edu.ifpb.appbuscarempregos.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

import br.edu.ifpb.appbuscarempregos.Sine;
import br.edu.ifpb.appbuscarempregos.activity.DetalharActivity;
import br.edu.ifpb.appbuscarempregos.asynctask.HttpGetAsyncTask;

/**
 * Created by Henrique on 29/10/2016.
 */

public class DetalharOnItemClickListener implements AdapterView.OnItemClickListener {
    private Activity activity;

    public DetalharOnItemClickListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       // HttpGetAsyncTask httpGetAsyncTask = new HttpGetAsyncTask();

       // ArrayList<Sine> sine = httpGetAsyncTask.execute("http://mobile-aceite.tcu.gov.br/mapa-da-saude/rest/emprego/cod/").get());

        Intent intent = new Intent(activity, DetalharActivity.class);
        activity.startActivity(intent);

    }
}
