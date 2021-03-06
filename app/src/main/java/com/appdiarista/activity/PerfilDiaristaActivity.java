package com.appdiarista.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.appdiarista.appdiarista.R;
import com.appdiarista.dao.DiaristaDao;
import com.appdiarista.model.Diarista;

import java.util.ArrayList;
import java.util.List;

public class PerfilDiaristaActivity extends AppCompatActivity {
    private String sobreMim;
    private String endereço;
    private String valor;
    private Double latitude;
    private Double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_diarista);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int idDiarista = extras.getInt("idDiarista");
        Diarista diarista = new DiaristaDao(this).buscaDiarista(idDiarista);
        if(diarista != null){
            sobreMim = diarista.getSobreMim();
            valor = String.valueOf(diarista.getValorDiaria()).replace(",","").replace(".",",");
            latitude = diarista.getLatitude();
            longitude = diarista.getLongitude();
        }
        ViewPager vp = (ViewPager) findViewById(R.id.vpPerfilDiarista);
        vp.setAdapter(new MyAdapter(getSupportFragmentManager()));

        TabLayout tl = (TabLayout) findViewById(R.id.tabLtPerfilDiarista);
        tl.setupWithViewPager(vp);
    }

    private class MyAdapter extends FragmentPagerAdapter{
        private final List<Fragment> fragments;
        private final List<String> titulos;
        public MyAdapter(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<Fragment>();
            Bundle b = new Bundle();
            b.putString("sobreMim",sobreMim);
            b.putString("valor",valor);
            b.putString("latitude",String.valueOf(latitude));
            b.putString("longitude",String.valueOf(longitude));
            b.putString("valor",valor);
            FrDetalhesDiaristaActivity frDetalhes = new FrDetalhesDiaristaActivity();
            frDetalhes.setArguments(b);
            fragments.add(frDetalhes);
            fragments.add(new FrAgendaDiaristaActivity());

            titulos = new ArrayList<String>();
            titulos.add("Detalhes");
            titulos.add("Agenda");
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulos.get(position);
        }
    }
}
