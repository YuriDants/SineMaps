package br.edu.ifpb.appbuscarempregos.activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import br.edu.ifpb.appbuscarempregos.R;
import br.edu.ifpb.appbuscarempregos.Sine;
import br.edu.ifpb.appbuscarempregos.listeners.DetalharOnItemClickListener;
import br.edu.ifpb.appbuscarempregos.listeners.PesquisarOnTextWatcherListener;

public class ListarBrasilActivity extends Activity {
    private ArrayAdapter<Sine> adapter;
    private List<Sine> listaBase;
    private ListView list;
    private static ImageView image;
    private static AnimationDrawable load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_brasil);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaBase);

        list = (ListView) findViewById(R.id.list);

        EditText searchText = (EditText) findViewById(R.id.txt);
        searchText.addTextChangedListener(new PesquisarOnTextWatcherListener(this));

        list.setOnItemClickListener(new DetalharOnItemClickListener(this));

        image = (ImageView) findViewById(R.id.imagebr);
        image.setBackgroundResource(R.drawable.load);

        load = (AnimationDrawable) image.getBackground();
        load.start();

    }

    public ArrayAdapter<Sine> getAdapter() {
        return adapter;
    }

    public void setAdapter(ArrayAdapter<Sine> adapter) {
        this.adapter = adapter;
        this.list.setAdapter(adapter);
    }

    public List<Sine> getListaBase() {
        return listaBase;
    }

    public void setListaBase(List<Sine> listaBase) {
        this.listaBase = listaBase;
    }

    public ListView getList() {
        return list;
    }

    public void setList() {
        adapter.notifyDataSetChanged();
        this.list.setAdapter(adapter);
    }

    public void setList(List<Sine> lista) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        this.list.setAdapter(adapter);
    }


    public static void stopAnimetion() {
        image.setVisibility(View.INVISIBLE);
        load.stop();
    }
}
