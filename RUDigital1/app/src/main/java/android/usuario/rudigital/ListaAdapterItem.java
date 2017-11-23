package android.usuario.rudigital;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pedro Junio on 23/11/2017.
 */

public class ListaAdapterItem extends ArrayAdapter<Cardapio> {

    private Context context;
    private ArrayList<Cardapio> lista;

    public ListaAdapterItem(Context context, ArrayList<Cardapio> lista) {
        super(context, R.layout.item, lista);
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Cardapio itemPosicao = this.lista.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.item, null);

        TextView nome = (TextView) convertView.findViewById(R.id.nome);
        nome.setText(itemPosicao.getNome());

        TextView periodo = (TextView) convertView.findViewById(R.id.periodo);
        periodo.setText("Período: " + itemPosicao.getDataInicio() + " / " + itemPosicao.getDataFinal());

        TextView pratos = (TextView) convertView.findViewById(R.id.pratos);
        pratos.setText(itemPosicao.toString());

        RadioButton voto = (RadioButton) convertView.findViewById(R.id.voto);

        return convertView;
    }
}
