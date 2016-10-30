package br.edu.ifpb.appbuscarempregos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.edu.ifpb.appbuscarempregos.R;
import br.edu.ifpb.appbuscarempregos.Sine;
import br.edu.ifpb.appbuscarempregos.asynctask.HttpGetAsyncTask;
import br.edu.ifpb.appbuscarempregos.listeners.DetalharOnItemClickListener;

public class ListarCGActivity extends Activity {
    private ArrayAdapter<Sine> adapter= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cg);

        ListView listView = (ListView) findViewById(R.id.list);
        HttpGetAsyncTask httpGetAsyncTask = new HttpGetAsyncTask();

        listView.setOnItemClickListener(new DetalharOnItemClickListener(this));

        try {
            List<Sine> list = httpGetAsyncTask.execute("http://mobile-aceite.tcu.gov.br/mapa-da-saude/rest/emprego/latitude/-7.242662/longitude/-35.9716057/raio/100").get();
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public ArrayAdapter<Sine> getAdapter() {
        return adapter;
    }

    public void setAdapter(ArrayAdapter<Sine> adapter) {
        this.adapter = adapter;
    }
}

