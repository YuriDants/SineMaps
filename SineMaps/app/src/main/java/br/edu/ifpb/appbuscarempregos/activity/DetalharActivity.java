package br.edu.ifpb.appbuscarempregos.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.util.concurrent.ExecutionException;

import br.edu.ifpb.appbuscarempregos.R;
import br.edu.ifpb.appbuscarempregos.Sine;
import br.edu.ifpb.appbuscarempregos.asynctask.HttpGetAsyncTask;
import br.edu.ifpb.appbuscarempregos.listeners.MapaOnClickListener;

public class DetalharActivity extends Activity {
    private TextView txtNome;
    private TextView txtendereco;
    private TextView txtEntidadeConveniada;
    private TextView txtCodPosto;
    private TextView txtBairro;
    private TextView txtCEP;
    private TextView txtTelefone;
    private TextView txtMunicipio;
    private TextView txtUF;
    private Button mapButton;

    private Sine sine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhar);

        txtNome = (TextView) findViewById(R.id.txtNome);
        //txtendereco = (TextView) findViewById(R.id.txtendereco);
        //txtEntidadeConveniada = (TextView) findViewById(R.id.txtEntidadeConveniada);
        //txtCodPosto = (TextView) findViewById(R.id.txtCodPosto);
        //txtBairro = (TextView) findViewById(R.id.txtBairro);
        //txtCEP = (TextView) findViewById(R.id.txtCEP);
        //txtTelefone = (TextView) findViewById(R.id.txtTelefone);
        //txtMunicipio = (TextView) findViewById(R.id.txtMunicipio);
        //txtUF = (TextView) findViewById(R.id.txtUF);

        mapButton = (Button) findViewById(R.id.mapButton);

        sine = (Sine) getIntent().getSerializableExtra("sine");

        txtNome.setText(sine.getNome());
        //txtendereco.setText(sine.getEndereco());
        //txtEntidadeConveniada.setText(sine.getEntidadeConveniada());
        //txtCodPosto.setText(sine.getCodPosto());
        //txtBairro.setText(sine.getBairro());
        //txtCEP.setText(sine.getCep());
        //txtTelefone.setText(sine.getTelefone());
        //txtMunicipio.setText(sine.getMunicipio());
        //txtUF.setText(sine.getUf());

        mapButton.setOnClickListener(new MapaOnClickListener(this));
    }

    public TextView getTxtNome() {
        return txtNome;
    }

    public void setTxtNome(TextView txtNome) {
        this.txtNome = txtNome;
    }

    public TextView getTxtendereco() {
        return txtendereco;
    }

    public void setTxtendereco(TextView txtendereco) {
        this.txtendereco = txtendereco;
    }

    public TextView getTxtEntidadeConveniada() {
        return txtEntidadeConveniada;
    }

    public void setTxtEntidadeConveniada(TextView txtEntidadeConveniada) {
        this.txtEntidadeConveniada = txtEntidadeConveniada;
    }

    public TextView getTxtCodPosto() {
        return txtCodPosto;
    }

    public void setTxtCodPosto(TextView txtCodPosto) {
        this.txtCodPosto = txtCodPosto;
    }

    public TextView getTxtBairro() {
        return txtBairro;
    }

    public void setTxtBairro(TextView txtBairro) {
        this.txtBairro = txtBairro;
    }

    public TextView getTxtCEP() {
        return txtCEP;
    }

    public void setTxtCEP(TextView txtCEP) {
        this.txtCEP = txtCEP;
    }

    public TextView getTxtTelefone() {
        return txtTelefone;
    }

    public void setTxtTelefone(TextView txtTelefone) {
        this.txtTelefone = txtTelefone;
    }

    public TextView getTxtMunicipio() {
        return txtMunicipio;
    }

    public void setTxtMunicipio(TextView txtMunicipio) {
        this.txtMunicipio = txtMunicipio;
    }

    public TextView getTxtUF() {
        return txtUF;
    }

    public void setTxtUF(TextView txtUF) {
        this.txtUF = txtUF;
    }

    public Button getMapButton() {
        return mapButton;
    }

    public void setMapButton(Button mapButton) {
        this.mapButton = mapButton;
    }

    public Sine getSine() {
        return sine;
    }

    public void setSine(Sine sine) {
        this.sine = sine;
    }
}
