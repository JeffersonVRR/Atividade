package com.example.jeffi.hospital;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAO {
    private CriarBanco banco;
    private SQLiteDatabase db;

    public DAO(Context context){
        banco = new CriarBanco(context);
    }

    public String insereDado(Integer num_leito, String nome_pac, Integer pres_art, Integer bat_card, Integer temp_corp){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriarBanco.NUM_LEITO, num_leito);
        valores.put(CriarBanco.NOME, nome_pac);
        valores.put(CriarBanco.PRESSAO_ARTERIAL, pres_art);
        valores.put(CriarBanco.BAT_CARDIACO, bat_card);
        valores.put(CriarBanco.TEMP_CORPO,temp_corp);

        resultado = db.insert(CriarBanco.TABELA, null, valores);
        db.close();

        if (resultado == -1){
            return "Erro ao adicionar dados";
        }else {
            return "Adicionado com sucesso";
        }
    }

    public Cursor listaDados(){
        Cursor cursor;
        String [] campos = {banco.NUM_LEITO,banco.NOME};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos,null,null,null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor listaDadosPorId(Integer num_leito){
        Cursor cursor;
        String [] campos = {banco.NUM_LEITO, banco.NOME, banco.PRESSAO_ARTERIAL, banco.BAT_CARDIACO, banco.TEMP_CORPO};
        String where = CriarBanco.NUM_LEITO + "=" + num_leito;
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, where,null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public void alterarDado(Integer num_leito, String nome_pac, Integer pres_art, Integer bat_card, Integer temp_corp){
        ContentValues valores;
        String where;
        db = banco.getWritableDatabase();
        where = CriarBanco.NUM_LEITO + "=" + num_leito;
        valores = new ContentValues();
        valores.put(CriarBanco.NOME, nome_pac);
        valores.put(CriarBanco.PRESSAO_ARTERIAL, pres_art);
        valores.put(CriarBanco.BAT_CARDIACO, bat_card);
        valores.put(CriarBanco.TEMP_CORPO,temp_corp);

        db.update(CriarBanco.TABELA,valores,where,null);
        db.close();
    }
    public void deletar(Integer num_leito){
        String where = CriarBanco.NUM_LEITO + "=" + num_leito;
        db = banco.getReadableDatabase();
        db.delete(CriarBanco.TABELA,where,null);
        db.close();
    }
}
