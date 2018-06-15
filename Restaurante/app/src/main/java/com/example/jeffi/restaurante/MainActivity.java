package com.example.jeffi.restaurante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText edt_consumo;
    private EditText edt_couvert_artistico;
    private EditText edt_dividir;
    private EditText edt_servico;
    private EditText edt_conta_total;
    private EditText edt_valor_pessoa;
    private Button bt_calcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_consumo = (EditText) findViewById(R.id.edt_consumo);
        edt_couvert_artistico = (EditText) findViewById(R.id.edt_couvert_artistico);
        edt_dividir = (EditText) findViewById(R.id.edt_dividir);
        edt_servico = (EditText) findViewById(R.id.edt_servico);
        edt_conta_total = (EditText) findViewById(R.id.edt_conta_total);
        edt_valor_pessoa =  (EditText) findViewById(R.id.edt_valor_pessoa);
        bt_calcular = (Button) findViewById(R.id.bt_calcular);

        bt_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double tx_servico = (Double.parseDouble(edt_consumo.getText().toString())) * 0.10 ;
                Double ct_total = ((Double.parseDouble(edt_consumo.getText().toString())) + (Double.parseDouble(edt_couvert_artistico.getText().toString())) + tx_servico);
                Double vl_pessoa = (ct_total /(Double.parseDouble(edt_dividir.getText().toString())));

                edt_servico.setText(String.format("%.2f", tx_servico));
                edt_conta_total.setText(String.format("%.2f", ct_total));
                edt_valor_pessoa.setText(String.format("%.2f", vl_pessoa));
            }
        });
    }
}
