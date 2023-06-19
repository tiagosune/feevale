package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class Historico extends AppCompatActivity {

    private TextView historicoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        historicoTextView = findViewById(R.id.txtHistorico);

        // Recupera as operações da intent
        List<String> operacoes = getIntent().getStringArrayListExtra("operacoes");

        exibirHistorico(operacoes);
    }

    private void exibirHistorico(List<String> operacoes) {
        StringBuilder builder = new StringBuilder();

        for (String operacao : operacoes) {
            builder.append(operacao);
            builder.append("\n-----------------\n");
        }

        historicoTextView.setText(builder.toString());
    }

    public void voltar(View v) {
        finish();
    }
}

