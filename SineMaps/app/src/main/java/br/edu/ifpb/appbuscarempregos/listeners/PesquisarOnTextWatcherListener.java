package br.edu.ifpb.appbuscarempregos.listeners;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.appbuscarempregos.Sine;
import br.edu.ifpb.appbuscarempregos.activity.ListarBrasilActivity;
import br.edu.ifpb.appbuscarempregos.activity.ListarCGActivity;
import br.edu.ifpb.appbuscarempregos.activity.ListarGPSActivity;

/**
 * Created by gabri on 30/10/2016.
 */

public class PesquisarOnTextWatcherListener implements TextWatcher {
    private ListarBrasilActivity listarBrasilActivity = null;
    private ListarCGActivity listarCGActivity = null;
    private ListarGPSActivity listarGPSActivity = null;

    public PesquisarOnTextWatcherListener(ListarBrasilActivity listarBrasilActivity) {
        this.listarBrasilActivity = listarBrasilActivity;
    }

    public PesquisarOnTextWatcherListener(ListarCGActivity listarCGActivity) {
        this.listarCGActivity = listarCGActivity;
    }

    public PesquisarOnTextWatcherListener(ListarGPSActivity listarGPSActivity) {
        this.listarGPSActivity = listarGPSActivity;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        List<Sine> list = new ArrayList<>();
        list.clear();

        if (listarBrasilActivity != null) {
            for (int j = 0; j < listarBrasilActivity.getListaBase().size(); j++) {
                if ((listarBrasilActivity.getListaBase().get(j).getBairro().matches(".*(?i)" + charSequence + ".*")) ||
                        (listarBrasilActivity.getListaBase().get(j).getCep().matches(".*(?i)" + charSequence + ".*")) ||
                        (listarBrasilActivity.getListaBase().get(j).getCodPosto().matches(".*(?i)" + charSequence + ".*")) ||
                        (listarBrasilActivity.getListaBase().get(j).getEndereco().matches(".*(?i)" + charSequence + ".*")) ||
                        (listarBrasilActivity.getListaBase().get(j).getEntidadeConveniada().matches(".*(?i)" + charSequence + ".*")) ||
                        (listarBrasilActivity.getListaBase().get(j).getMunicipio().matches(".*(?i)" + charSequence + ".*")) ||
                        (listarBrasilActivity.getListaBase().get(j).getNome().matches(".*(?i)" + charSequence + ".*")) ||
                        (listarBrasilActivity.getListaBase().get(j).getTelefone().matches(".*(?i)" + charSequence + ".*")) ||
                        (listarBrasilActivity.getListaBase().get(j).getUf().matches(".*(?i)" + charSequence + ".*"))) {

                    list.add(listarBrasilActivity.getListaBase().get(j));
                }
            }
            listarBrasilActivity.setList(list);

        } else {
            if (listarCGActivity != null) {
                for (int j = 0; j < listarCGActivity.getListaBase().size(); j++) {
                    if ((listarCGActivity.getListaBase().get(j).getBairro().matches(".*(?i)" + charSequence + ".*")) ||
                            (listarCGActivity.getListaBase().get(j).getCep().matches(".*(?i)" + charSequence + ".*")) ||
                            (listarCGActivity.getListaBase().get(j).getCodPosto().matches(".*(?i)" + charSequence + ".*")) ||
                            (listarCGActivity.getListaBase().get(j).getEndereco().matches(".*(?i)" + charSequence + ".*")) ||
                            (listarCGActivity.getListaBase().get(j).getEntidadeConveniada().matches(".*(?i)" + charSequence + ".*")) ||
                            (listarCGActivity.getListaBase().get(j).getMunicipio().matches(".*(?i)" + charSequence + ".*")) ||
                            (listarCGActivity.getListaBase().get(j).getNome().matches(".*(?i)" + charSequence + ".*")) ||
                            (listarCGActivity.getListaBase().get(j).getTelefone().matches(".*(?i)" + charSequence + ".*")) ||
                            (listarCGActivity.getListaBase().get(j).getUf().matches(".*(?i)" + charSequence + ".*"))) {

                        list.add(listarCGActivity.getListaBase().get(j));
                    }
                }
                listarCGActivity.setList(list);

            } else {
                for (int j = 0; j < listarGPSActivity.getListaBase().size(); j++) {
                    if ((listarGPSActivity.getListaBase().get(j).getBairro().matches(".*(?i)" + charSequence + ".*")) ||
                            (listarGPSActivity.getListaBase().get(j).getCep().matches(".*(?i)" + charSequence + ".*")) ||
                            (listarGPSActivity.getListaBase().get(j).getCodPosto().matches(".*(?i)" + charSequence + ".*")) ||
                            (listarGPSActivity.getListaBase().get(j).getEndereco().matches(".*(?i)" + charSequence + ".*")) ||
                            (listarGPSActivity.getListaBase().get(j).getEntidadeConveniada().matches(".*(?i)" + charSequence + ".*")) ||
                            (listarGPSActivity.getListaBase().get(j).getMunicipio().matches(".*(?i)" + charSequence + ".*")) ||
                            (listarGPSActivity.getListaBase().get(j).getNome().matches(".*(?i)" + charSequence + ".*")) ||
                            (listarGPSActivity.getListaBase().get(j).getTelefone().matches(".*(?i)" + charSequence + ".*")) ||
                            (listarGPSActivity.getListaBase().get(j).getUf().matches(".*(?i)" + charSequence + ".*"))) {

                        list.add(listarGPSActivity.getListaBase().get(j));
                    }
                }
                listarGPSActivity.setList(list);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

}
