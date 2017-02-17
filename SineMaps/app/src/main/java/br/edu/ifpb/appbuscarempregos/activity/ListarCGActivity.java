package br.edu.ifpb.appbuscarempregos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.edu.ifpb.appbuscarempregos.R;
import br.edu.ifpb.appbuscarempregos.Sine;
import br.edu.ifpb.appbuscarempregos.asynctask.HttpGetAsyncTask;
import br.edu.ifpb.appbuscarempregos.asynctask.HttpGetSineSimplesAsyncTask;
import br.edu.ifpb.appbuscarempregos.listeners.DetalharOnItemClickListener;
import br.edu.ifpb.appbuscarempregos.listeners.MapsOnClickListener;
import br.edu.ifpb.appbuscarempregos.listeners.PesquisarOnTextWatcherListener;

public class ListarCGActivity extends Activity {
    private ArrayAdapter<Sine> adapter = null;
    private List<Sine> listaBase;
    private ListView list;
    private Button mapSines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cg);

        list = (ListView) findViewById(R.id.list);
        HttpGetSineSimplesAsyncTask httpGetAsyncTask = new HttpGetSineSimplesAsyncTask();

        EditText searchText = (EditText) findViewById(R.id.SearchText);
        searchText.addTextChangedListener(new PesquisarOnTextWatcherListener(this));

        list.setOnItemClickListener(new DetalharOnItemClickListener(this));

        mapSines = (Button) findViewById(R.id.mapSines);
        mapSines.setOnClickListener(new MapsOnClickListener(this));

        try {
            listaBase = httpGetAsyncTask.execute("http://mobile-aceite.tcu.gov.br/mapa-da-saude/rest/emprego/latitude/-7.219204/longitude/-35.882901/raio/100").get();
            setList(listaBase);
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

    public List<Sine> getListaBase() {
        return listaBase;
    }

    public void setListaBase(List<Sine> listaBase) {
        this.listaBase = listaBase;
    }

    public ListView getList() {
        return list;
    }

    public void setList(List<Sine> lista) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        list.setAdapter(adapter);
    }
}

