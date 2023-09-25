package com.example.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declarando variáveis
    private EditText editValor;
    private TextView textPorcentagem, textGorjeta, textTotal;
    private SeekBar seekBarGorjeta;

    private double porcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Direcionado variáveis aos seus campos na tela
        editValor = findViewById(R.id.editValor);
        textPorcentagem = findViewById(R.id.textPorcentagem);
        textGorjeta = findViewById(R.id.textGorjeta);
        textTotal = findViewById(R.id.textTotal);
        seekBarGorjeta = findViewById(R.id.seekBarGorjeta);

        // Adicioando um Listener para o SeekBar
        seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentagem = progress;
                textPorcentagem.setText(Math.round(porcentagem) + "%");

                if (editValor.getText().toString() == null || editValor.getText().toString().equals("")) {
                    seekBarGorjeta.setProgress(0);
                    textGorjeta.setText("RS 0.00");
                    textTotal.setText("RS 0.00");

                } else {
                    calcular();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public void calcular() {
        String valorRecuperado = editValor.getText().toString();

        // '.equals' é utilizado para comparações de textos.
        if (valorRecuperado == null || valorRecuperado.equals("")) {

            // Mensagem de erro.
            Toast.makeText(
                    getApplicationContext(),
                    "Digite um valor primeiro!",
                    Toast.LENGTH_LONG
            ).show();

        } else {
            // Converter string para double
            double valorDigitado = Double.parseDouble(valorRecuperado);

            // Calcular gorjeta
            double gorgeta = valorDigitado * (porcentagem / 100);
            double total = gorgeta + valorDigitado;

            // Vamos exibir o valor da gorgeta
            textGorjeta.setText("R$ " + Math.round(gorgeta));

            // Vamos exibir o valor total somado com o valor da gorjeta
            textTotal.setText("R$ " + total);
        }
    }
}