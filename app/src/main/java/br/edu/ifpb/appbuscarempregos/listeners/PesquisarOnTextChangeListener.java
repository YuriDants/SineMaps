package br.edu.ifpb.appbuscarempregos.listeners;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.appbuscarempregos.Sine;
import br.edu.ifpb.appbuscarempregos.activity.ListarBrasilActivity;
import br.edu.ifpb.appbuscarempregos.activity.ListarCGActivity;

/**
 * Created by gabri on 30/10/2016.
 */

public class PesquisarOnTextChangeListener implements TextWatcher {

    private ListarBrasilActivity listarBrasilActivity = null;
    private ListarCGActivity listarCGActivity = null;

    public PesquisarOnTextChangeListener(ListarBrasilActivity listarBrasilActivity) {
        this.listarBrasilActivity = listarBrasilActivity;
    }

    public PesquisarOnTextChangeListener(ListarCGActivity listarCGActivity) {
        this.listarCGActivity = listarCGActivity;
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
            listarCGActivity.setList(list);

        } else {
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

        }

    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

}
