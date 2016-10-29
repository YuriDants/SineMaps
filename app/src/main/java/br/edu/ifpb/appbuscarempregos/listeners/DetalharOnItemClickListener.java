package br.edu.ifpb.appbuscarempregos.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import br.edu.ifpb.appbuscarempregos.activity.DetalharActivity;

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
        Intent intent = new Intent(activity, DetalharActivity.class);
        activity.startActivity(intent);

    }
}
