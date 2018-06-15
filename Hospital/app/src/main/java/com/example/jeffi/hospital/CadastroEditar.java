package com.example.jeffi.hospital;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroEditar extends AppCompatActivity{
    EditText num_leito;
    EditText nome_pesso;
    EditText press_art;
    EditText bat_card;
    EditText temp_corp;
    Button salva;
    Button deleta;
    DAO dao;
    Cursor cursos;
    String codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_editar);
        codigo = this.getIntent().getStringExtra("codigo");
        dao = new DAO(getBaseContext());

        num_leito = (EditText) findViewById(R.id.num_leito);
        nome_pesso = (EditText) findViewById(R.id.nome_paciente);
        press_art = (EditText) findViewById(R.id.pres_art);
        bat_card = (EditText) findViewById(R.id.bat_card);
        temp_corp = (EditText) findViewById(R.id.temp_corpo);

        cursos = dao.listaDadosPorId(Integer.parseInt(codigo));
        num_leito.setText(cursos.getString(cursos.getColumnIndexOrThrow(CriarBanco.NUM_LEITO)));
        nome_pesso.setText(cursos.getString(cursos.getColumnIndexOrThrow(CriarBanco.NOME)));
        press_art.setText(cursos.getString(cursos.getColumnIndexOrThrow(CriarBanco.PRESSAO_ARTERIAL)));
        bat_card.setText(cursos.getString(cursos.getColumnIndexOrThrow(CriarBanco.BAT_CARDIACO)));
        temp_corp.setText(cursos.getString(cursos.getColumnIndexOrThrow(CriarBanco.TEMP_CORPO)));

        salva = (Button) findViewById(R.id.salvar);
        salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.alterarDado(Integer.parseInt(codigo), nome_pesso.getText().toString(),
                        Integer.parseInt(press_art.getText().toString()), Integer.parseInt(bat_card.getText().toString()),
                        Integer.parseInt(temp_corp.getText().toString()));
                Intent intent = new Intent(CadastroEditar.this,Consulta.class);
                startActivity(intent);
                finish();
            }
        });

        deleta = (Button) findViewById(R.id.deletar);
        deleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.deletar(Integer.parseInt(codigo));
                Intent intent = new Intent(CadastroEditar.this,Consulta.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, Consulta.class));
        return;
    }
}
