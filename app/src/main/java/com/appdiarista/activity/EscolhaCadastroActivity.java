package com.appdiarista.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.appdiarista.appdiarista.R;

public class EscolhaCadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_cadastro);
    }

    public void irParaCadDiarista(View v){
        Intent intent = new Intent(this, CadDiaristaActivity.class);
        startActivity(intent);
    }
    public void irParaCadContratante(View v){
        Intent intent = new Intent(this, CadContratanteActivity.class);
        startActivity(intent);

    }
}
