package com.example.placasdetransito;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView placa;
    private Button opcao1_;
    private Button opcao2_;
    private Button opcao3_;
    private Button opcao4_;

    private int currentQuestionIndex = 0;
    private int acertos = 0;

    private final int[] placasIds ={
            R.drawable.proibidoestacionar,
            R.drawable.proibidoestacionareparar,
            R.drawable.pistairregular,
            R.drawable.ponteestreita,
            R.drawable.lombada
    };

    private final String[][] alternativas = {
            {"Proibido Estacionar", "Pista Estreita", "Proibido Estacionar e parar", "Pista Irregular"},
            {"Uso obrigatorio de corrente", "Siga em frente ou a esquerda", "Proibido parar e estacionar", "Proibido retornar a direita"},
            {"Siga em frente", "Curva a esquerda", "Pista irregular", "Curva acentuada a esquerda"},
            {"Siga em frente", "Sentido circular na rota", "Pista irregular", "Ponte estreita"},
            {"Saliencia ou lombada ", "Semaforo a frente", "Advertencia Inform ", "Cruzamento de vias"}
            // adicione aqui as alternativas para cada questão
    };

    private final int[] respostasCorretas = {0, 2, 2, 3, 0};
    // adicione aqui o índice da resposta correta para cada questão (índices vão de 0 a 3)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        opcao1_ = findViewById(R.id.opcao1);
        opcao2_ = findViewById(R.id.opcao2);
        opcao3_ = findViewById(R.id.opcao3);
        opcao4_ = findViewById(R.id.opcao4);
        placa = findViewById(R.id.imgPlacas);

        opcao1_.setOnClickListener(this);
        opcao2_.setOnClickListener(this);
        opcao3_.setOnClickListener(this);
        opcao4_.setOnClickListener(this);
        questionario();


    }

    @Override
    public void onClick(View view) {
        if (view instanceof Button) {
            Button button = (Button) view;
            String resposta = button.getText().toString();

            if (resposta.equals(alternativas[currentQuestionIndex][respostasCorretas[currentQuestionIndex]])) {
                // Resposta está correta
                acertos++;
            }

            currentQuestionIndex++;
            if (currentQuestionIndex < placasIds.length) {
                questionario();
            } else {
                // Finalizou todas as questões
                // TODO: exibir resultado final
                exibirResultadoFinal();
            }
        }
    }
    private void questionario() {
        int placaId = placasIds[currentQuestionIndex];
        String[] alternativasQuestao = alternativas[currentQuestionIndex];

        placa.setImageResource(placaId);
        opcao1_.setText(alternativasQuestao[0]);
        opcao2_.setText(alternativasQuestao[1]);
        opcao3_.setText(alternativasQuestao[2]);
        opcao4_.setText(alternativasQuestao[3]);
    }
    private void exibirResultadoFinal() {
        String mensagem = "Você acertou " + acertos + " questões!";
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
        reiniciarQuiz();
    }
    private void reiniciarQuiz() {
        currentQuestionIndex = 0;
        acertos = 0;
        questionario();
    }
}
