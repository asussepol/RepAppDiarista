package com.appdiarista.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.appdiarista.adapter.ContratanteAdapter;
import com.appdiarista.appdiarista.R;
import com.appdiarista.dao.ContratanteDao;
import com.appdiarista.model.Contratante;

import java.util.List;

public class ListaContratanteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contratante);
        ListView lv = (ListView) findViewById(R.id.listaContratantesCadastrados);
        List<Contratante> contratantes = new ContratanteDao(this).listarAll();
        ContratanteAdapter adapter = new ContratanteAdapter(this,contratantes);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListaContratanteActivity.this, PerfilDiaristaActivity.class);
                Bundle bd = new Bundle();
                Long idDr = id;
                bd.putInt("idContratante", Integer.valueOf(idDr.toString()));
                intent.putExtras(bd);
                startActivity(intent);
            }
        });

    }
}
