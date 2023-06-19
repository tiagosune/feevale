package com.example.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculadora.Historico;
import com.example.calculadora.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView expressaoTextView;

    private List<String> operacoes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expressaoTextView = findViewById(R.id.expressao);

        setOnClickListeners();

        operacoes = new ArrayList<>();

    }

    private void setOnClickListeners() {
        // Números
        findViewById(R.id.btNum0).setOnClickListener(this);
        findViewById(R.id.btNum1).setOnClickListener(this);
        findViewById(R.id.btNum2).setOnClickListener(this);
        findViewById(R.id.btNum3).setOnClickListener(this);
        findViewById(R.id.btNum4).setOnClickListener(this);
        findViewById(R.id.btNum5).setOnClickListener(this);
        findViewById(R.id.btNum6).setOnClickListener(this);
        findViewById(R.id.btNum7).setOnClickListener(this);
        findViewById(R.id.btNum8).setOnClickListener(this);
        findViewById(R.id.btNum9).setOnClickListener(this);

        // Operações
        findViewById(R.id.btDividir).setOnClickListener(this);
        findViewById(R.id.btMultiplicar).setOnClickListener(this);
        findViewById(R.id.btSubtrair).setOnClickListener(this);
        findViewById(R.id.btPonto).setOnClickListener(this);
        findViewById(R.id.btSomar).setOnClickListener(this);

        // Botões funcionais
        findViewById(R.id.btApagar).setOnClickListener(this);
        findViewById(R.id.btLimpar).setOnClickListener(this);
        findViewById(R.id.btIgual).setOnClickListener(this);
        findViewById(R.id.btHistorico).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btHistorico) {
            Intent it = new Intent(getBaseContext(), Historico.class);
            it.putStringArrayListExtra("operacoes", (ArrayList<String>) operacoes);
            startActivity(it);
        } else if (view.getId() == R.id.btLimpar) {
            limparCalculadora();
        } else if (view.getId() == R.id.btApagar) {
            apagarNumero();
        } else if (view.getId() == R.id.btIgual) {
            calcularExpressao();
        } else {
            Button botao = (Button) view;
            adicionarCaractere(botao.getText().toString());
        }
    }

    private void calcularExpressao() {
        String expressao = expressaoTextView.getText().toString();

        try {
            Expression e = new ExpressionBuilder(expressao).build();
            double resultado = e.evaluate();

            expressaoTextView.setText(String.valueOf(resultado));
            String operacao = expressao + " = " + resultado;
            operacoes.add(operacao);

        } catch (Exception ex) {
            expressaoTextView.setText("Erro");
        }

    }


    private void limparCalculadora() {
        expressaoTextView.setText("");
    }

    private void adicionarCaractere(String caractere) {
        if (caractere.equals("X")) {
            caractere = "*";
        }
        String expressaoAtual = expressaoTextView.getText().toString();
        expressaoTextView.setText(expressaoAtual + caractere);
    }

    private void apagarNumero() {
        String expressaoAtual = expressaoTextView.getText().toString();
        if (!expressaoAtual.isEmpty()) {
            expressaoAtual = expressaoAtual.substring(0, expressaoAtual.length() - 1);
            expressaoTextView.setText(expressaoAtual);
        }
    }
}