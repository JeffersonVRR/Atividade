package com.example.jeffi.hospital;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText num_leito;
    EditText nome_pesso;
    EditText press_art;
    EditText bat_card;
    EditText temp_corp;
    Button salva;
    Button lista;
    Cursor cursos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num_leito = (EditText) findViewById(R.id.num_leito);
        nome_pesso = (EditText) findViewById(R.id.nome_paciente);
        press_art = (EditText) findViewById(R.id.pres_art);
        bat_card = (EditText) findViewById(R.id.bat_card);
        temp_corp = (EditText) findViewById(R.id.temp_corpo);
        salva = (Button) findViewById(R.id.salvar);
        lista = (Button) findViewById(R.id.lista);

        salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO dao = new DAO(getBaseContext());

                String resultado;
                resultado = dao.insereDado(Integer.parseInt(num_leito.getText().toString()),nome_pesso.getText().toString(),
                        Integer.parseInt(press_art.getText().toString()),Integer.parseInt(bat_card.getText().toString()),
                        Integer.parseInt(temp_corp.getText().toString()));

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, Consulta.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
