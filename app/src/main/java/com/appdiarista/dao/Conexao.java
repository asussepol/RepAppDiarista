package com.appdiarista.dao;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by FR£D on 09/09/2017.
 */

public class Conexao {
    private static SQLiteDatabase db;

    public static SQLiteDatabase  getBd(){
        if(db == null) {
            db = SQLiteDatabase.openOrCreateDatabase("/sdcard/bd_interno/diaristaapp.db", null);
            if(db.isOpen()){
                Log.i("msg","open");
            }else{
                Log.i("msg","fechado");
            }
//            db. execSQL(DataBaseSql.CTTIPOUSUARIO);
//            db. execSQL(DataBaseSql.CTUSUARIO);
//            db.execSQL("insert into tipoUsuario(id,descricao) values (1,'DIARISTA');");
//            db.execSQL("insert into tipoUsuario(id,descricao) values (2,'CONTRATANTE');");
//            db.execSQL("insert into usuario (login,senha,tipoUsuario_id) values('fredericond@hotmail.com','1234',2);");

        }
        return db;
    }
}
