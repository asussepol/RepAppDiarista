package com.appdiarista.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appdiarista.appdiarista.R;
import com.appdiarista.model.Contratante;

import java.util.List;

/**
 * Created by César Lopes on 16/09/2017.
 */

public class ContratanteAdapter  extends BaseAdapter{

    private Context ctx;
    private List<Contratante> contratantes;

    public ContratanteAdapter(Context ctx, List<Contratante> contratantes){

        this.ctx=ctx;
        this.contratantes=contratantes;
    }
    @Override
    public int getCount() {
        return contratantes.size();
    }

    @Override
    public Object getItem(int position) {
        return contratantes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contratantes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Contratante contratante= contratantes.get(position);
        View v= LayoutInflater.from(ctx).inflate(R.layout.list_contrantes,parent,false);
        ImageView iv = (ImageView) v.findViewById(R.id.imgLogo);
        iv.setImageResource(R.drawable.icon_user_q);
        TextView tvNome = (TextView) v.findViewById(R.id.txtNome);
        tvNome.setText(contratante.getNome());
        TextView tvSobreMim = (TextView) v.findViewById(R.id.txtSobreMim);
        tvSobreMim.setText(contratante.getSobreMim());
        TextView tvTelefone = (TextView) v.findViewById(R.id.txtTelefone);
        tvSobreMim.setText(contratante.getTelefone());
        return v;
    }
}
