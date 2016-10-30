package br.edu.ifpb.appbuscarempregos.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.edu.ifpb.appbuscarempregos.R;
import br.edu.ifpb.appbuscarempregos.Sine;
import br.edu.ifpb.appbuscarempregos.asynctask.HttpGetAsyncTask;
import br.edu.ifpb.appbuscarempregos.listeners.DetalharOnItemClickListener;

public class ListarGpsActivity extends Activity {
    private ArrayAdapter<Sine> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_gps);

        ListView listView = (ListView) findViewById(R.id.list);
        HttpGetAsyncTask httpGetAsyncTask = new HttpGetAsyncTask();

        listView.setOnItemClickListener(new DetalharOnItemClickListener(this));

        String lat = getIntent().getStringExtra("lat");
        String longi = (String) getIntent().getStringExtra("longi");
        String url = "http://mobile-aceite.tcu.gov.br/mapa-da-saude/rest/emprego//latitude/" + lat + "/longitude/" + "longi" + "/raio/{raio}";

        try {
            List<Sine> list = httpGetAsyncTask.execute(url).get();
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
