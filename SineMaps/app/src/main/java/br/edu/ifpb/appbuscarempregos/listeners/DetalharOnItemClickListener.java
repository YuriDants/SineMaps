package br.edu.ifpb.appbuscarempregos.listeners;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.appbuscarempregos.R;
import br.edu.ifpb.appbuscarempregos.Sine;
import br.edu.ifpb.appbuscarempregos.activity.DetalharActivity;
import br.edu.ifpb.appbuscarempregos.activity.ListarBrasilActivity;
import br.edu.ifpb.appbuscarempregos.activity.ListarCGActivity;
import br.edu.ifpb.appbuscarempregos.activity.ListarGPSActivity;
import br.edu.ifpb.appbuscarempregos.asynctask.HttpGetAsyncTask;

/**
 * Created by Henrique on 29/10/2016.
 */

public class DetalharOnItemClickListener implements AdapterView.OnItemClickListener {
    private ListarCGActivity listarCGActivity = null;
    private ListarBrasilActivity listarBrasilActivity = null;
    private ListarGPSActivity listarGPSActivity = null;

    public DetalharOnItemClickListener(ListarCGActivity activity) {
        this.listarCGActivity = activity;
    }

    public DetalharOnItemClickListener(ListarBrasilActivity activity) {
        this.listarBrasilActivity = activity;
    }

    public DetalharOnItemClickListener(ListarGPSActivity activity) {
        this.listarGPSActivity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if (listarBrasilActivity != null) {
            ArrayAdapter adapter = listarBrasilActivity.getAdapter();
            Sine sine = (Sine) adapter.getItem(i);

            Intent intent = new Intent(listarBrasilActivity, DetalharActivity.class);
            intent.putExtra("sine", sine);

            listarBrasilActivity.startActivity(intent);
        } else {
            if (listarCGActivity != null) {
                ArrayAdapter adapter = listarCGActivity.getAdapter();
                Sine sine = (Sine) adapter.getItem(i);

                Intent intent = new Intent(listarCGActivity, DetalharActivity.class);
                intent.putExtra("sine", sine);

                listarCGActivity.startActivity(intent);
            } else {
                    ArrayAdapter adapter = listarGPSActivity.getAdapter();
                    Sine sine = (Sine) adapter.getItem(i);

                    Intent intent = new Intent(listarGPSActivity, DetalharActivity.class);
                    intent.putExtra("sine", sine);

                    listarGPSActivity.startActivity(intent);
                }
        }

    }
}