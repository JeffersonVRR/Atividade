package com.example.jeffi.hospital;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriarBanco extends SQLiteOpenHelper{
    private static final String NOME_BANCO = "Banco.db";
    public static final String TABELA = "paciente";
    public static final String NUM_LEITO = "numeroLeito";
    public static final String NOME = "nome";
    public static final String PRESSAO_ARTERIAL = "pressaoArterial";
    public static final String BAT_CARDIACO = "batimentoCardiaco";
    public static final String TEMP_CORPO = "temperaturaCorpo";
    private static final int VERSAO = 1;

    public CriarBanco(Context context){
        super(context, NOME_BANCO, null, VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + NUM_LEITO + " integer primary key not null,"
                + NOME + " text not null,"
                + PRESSAO_ARTERIAL + " integer not null,"
                + BAT_CARDIACO + " integer not null,"
                + TEMP_CORPO + " integer not null" + ")";

        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
