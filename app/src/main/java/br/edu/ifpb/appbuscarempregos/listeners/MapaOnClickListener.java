package br.edu.ifpb.appbuscarempregos.listeners;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import br.edu.ifpb.appbuscarempregos.activity.DetalharActivity;

/**
 * Created by Henrique on 29/10/2016.
 */

public class MapaOnClickListener implements View.OnClickListener {
    private DetalharActivity activity;

    public MapaOnClickListener(DetalharActivity detalharActivity) {
        this.activity = detalharActivity;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.br/maps/search/" + activity.getSine().getLat() + "+" + activity.getSine().getLongi()));
        activity.startActivity(intent);
    }
}
