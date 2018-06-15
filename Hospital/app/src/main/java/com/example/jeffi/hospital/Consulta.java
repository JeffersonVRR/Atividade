package com.example.jeffi.hospital;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Consulta extends AppCompatActivity{

    private ListView lista;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta);
        lista = (ListView) findViewById(R.id.lista);

        final Cursor cursor;
        DAO dao = new DAO(getBaseContext());
        cursor = dao.listaDados();
        final NoIdCursorWrapper nc = new NoIdCursorWrapper(cursor, CriarBanco.NUM_LEITO);
        final String [] nomeCampo = {CriarBanco.NUM_LEITO,CriarBanco.NOME};
        int [] idViews = {R.id.num_leito,R.id.nome_paciente};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),R.layout.consulta,nc,nomeCampo,idViews);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                //cursor.moveToPosition(position);
                nc.moveToPosition(position);
                codigo = nc.getString(nc.getColumnIndexOrThrow(CriarBanco.NUM_LEITO));
                //codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriarBanco.NUM_LEITO));
                Intent intent = new Intent(Consulta.this, CadastroEditar.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MainActivity.class));
        return;
    }
}
