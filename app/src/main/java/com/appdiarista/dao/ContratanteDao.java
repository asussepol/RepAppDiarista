package com.appdiarista.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import com.appdiarista.model.Contratante;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

/**
 * Created by César Lopes on 16/09/2017.
 */

public class ContratanteDao {


    private DataBaseHelper db;

    public ContratanteDao(Context context) {
        db = new DataBaseHelper(context);
    }


    public void inserir(Contratante contratante)throws SQLiteException, Exception{

        ContentValues valuesUs = new ContentValues();
        ContentValues valuesDr = new ContentValues();

        valuesDr.put("nome",contratante.getNome());
        valuesDr.put("telefone",contratante.getTelefone());
        valuesDr.put("dataNascimento", String.valueOf(contratante.getDataNascimento()));
        valuesDr.put("cpf",contratante.getCpf());
        valuesDr.put("email", contratante.getEmail());
        valuesUs.put("login", contratante.getEmail());
        valuesUs.put("tipoUsuario_id", 1);
        valuesDr.put("telefone", contratante.getTelefone());
        valuesDr.put("sobreMim", contratante.getSobreMim());
        valuesDr.put("longitude", contratante.getLongitude());
        valuesDr.put("latitude", contratante.getLatitude());
        long nr = db.getWritableDatabase().insert("contratante", null, valuesDr);
        long nrU = 0;




    }

    public List<Contratante> listarAll(){

        List<Contratante> contratantes = new ArrayList<Contratante>();

        Cursor cursor = db.getReadableDatabase().rawQuery("select id,nome,sobreMim, telefone from contratante", new String[]{});
        Contratante contratante;
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String sobreMim = cursor.getString(cursor.getColumnIndex("sobreMim"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
            contratante = new Contratante(id,nome,telefone,sobreMim);
            contratantes.add(contratante);
        }


        return contratantes;
    }


    public Contratante buscarContratante(){
        Contratante contratante=null;

        String sql = "select id,nome,sobreMim,telefone, latitude, longitude from contratante where id=?";
        Cursor cursor = db.getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(id)});
        while(cursor.moveToNext()){
            int idx = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String sobreMim = cursor.getString(cursor.getColumnIndex("sobreMim"));
            String telefone=cursor.getString(cursor.getColumnIndex("telefone"));
            double latitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
            double longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
            contratante= new Contratante(idx,sobreMim,telefone,latitude,longitude);


        }




        return contratante;
    }
}
