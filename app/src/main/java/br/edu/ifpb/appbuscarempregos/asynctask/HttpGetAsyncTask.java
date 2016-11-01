package br.edu.ifpb.appbuscarempregos.asynctask;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.appbuscarempregos.Sine;
import br.edu.ifpb.appbuscarempregos.activity.ListarBrasilActivity;
import br.edu.ifpb.appbuscarempregos.activity.ListarCGActivity;
import br.edu.ifpb.appbuscarempregos.activity.MainActivity;

/**
 * Created by gabriel on 29/10/2016.
 */

public class HttpGetAsyncTask extends AsyncTask<String, Void, List<Sine>> {
    private ListarBrasilActivity listarBrasilActivity;
    private ListarCGActivity listarCGActivity;
    private MainActivity main;
    private int tipo;

    public HttpGetAsyncTask(MainActivity main, int tipo) {
        this.main = main;
        this.tipo = tipo;
    }


    @Override
    protected void onPreExecute() {
        Intent intent;

        switch (tipo) {
            case 1:
                listarBrasilActivity = new ListarBrasilActivity();

                intent = new Intent(main, listarBrasilActivity.getClass());
                main.startActivity(intent);

                break;
            case 2:
                listarCGActivity = new ListarCGActivity();

                intent = new Intent(main, listarCGActivity.getClass());
                main.startActivity(intent);
            default:
                break;
        }
    }

    @Override
    protected List<Sine> doInBackground(String... strings) {
        HttpURLConnection connection = null;
        JsonReader jsonReader = null;
        List<Sine> sines = new ArrayList<>();

        try {
            URL url = new URL(strings[0]);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

            sines = getSines(jsonReader);

            inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

            try {
                if (jsonReader != null) {
                    jsonReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return sines;
        }
    }

    public List<Sine> getSines(JsonReader jsonReader) throws IOException {
        List<Sine> sines = new ArrayList<>();
        jsonReader.beginArray();

        while (jsonReader.hasNext()) {
            sines.add(getSine(jsonReader));
        }

        jsonReader.endArray();
        return sines;
    }

    public Sine getSine(JsonReader jsonReader) throws IOException {
        Sine sine = new Sine();

        jsonReader.beginObject();

        while (jsonReader.hasNext()) {
            String obj = jsonReader.nextName();

            if (obj.equals("codPosto")) {
                sine.setCodPosto(jsonReader.nextString());

            } else if (obj.equals("nome")) {
                sine.setNome(jsonReader.nextString());

            } else if (obj.equals("entidadeConveniada")) {
                sine.setEntidadeConveniada(jsonReader.nextString());

            } else if (obj.equals("endereco")) {
                sine.setEndereco(jsonReader.nextString());

            } else if (obj.equals("bairro")) {
                sine.setBairro(jsonReader.nextString());

            } else if (obj.equals("cep")) {
                sine.setCep(jsonReader.nextString());

            } else if (obj.equals("telefone")) {
                sine.setTelefone(jsonReader.nextString());

            } else if (obj.equals("municipio")) {
                sine.setMunicipio(jsonReader.nextString());

            } else if (obj.equals("uf")) {
                sine.setUf(jsonReader.nextString());

            } else if (obj.equals("lat")) {
                sine.setLat(jsonReader.nextString());

            } else if (obj.equals("long")) {

                sine.setLongi(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
            }
        }

        jsonReader.endObject();
        return sine;
    }


    @Override
    protected void onPostExecute(List<Sine> result) {
        super.onPostExecute(result);
        switch (tipo) {
            case 1:
                listarBrasilActivity.stopAnimetion();
                listarBrasilActivity.setListaBase(result);
                listarBrasilActivity.setAdapter(new ArrayAdapter<Sine>(listarBrasilActivity, android.R.layout.simple_list_item_1, result));
                break;
            case 2:
                break;
        }
    }

}
