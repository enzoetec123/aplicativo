import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var perguntas: List<Questao>
    private var indicePerguntaAtual = 0
    private var pontuacao = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val moduloIndex = intent.getIntExtra("moduloIndex", 0)
        perguntas = obterPerguntasDoModulo(moduloIndex)

        exibirPergunta()
    }

    private fun exibirPergunta() {
        if (indicePerguntaAtual < perguntas.size) {
            val perguntaAtual = perguntas[indicePerguntaAtual]

            val textViewPergunta: TextView = findViewById(R.id.textViewPergunta)
            val radioGroup: RadioGroup = findViewById(R.id.radioGroupOpcoes)

            textViewPergunta.text = perguntaAtual.pergunta
            radioGroup.clearCheck()

            perguntaAtual.opcoes.forEachIndexed { index, opcao ->
                val radioButton = RadioButton(this)
                radioButton.id = index
                radioButton.text = opcao
                radioGroup.addView(radioButton)
            }

            val buttonResponder: Button = findViewById(R.id.buttonResponder)
            buttonResponder.setOnClickListener {
                val opcaoSelecionada = radioGroup.checkedRadioButtonId
                if (opcaoSelecionada == perguntaAtual.respostaCorreta) {
                    pontuacao++
                }
                indicePerguntaAtual++
                exibirPergunta()
            }
        } else {
            exibirResultado()
        }
    }

    private fun exibirResultado() {
        // Exibir a pontuação final
        val textViewPergunta: TextView = findViewById(R.id.textViewPergunta)
        textViewPergunta.text = "Pontuação: $pontuacao/${perguntas.size}"
    }

    private fun obterPerguntasDoModulo(moduloIndex: Int): List<Questao> {
        // Retorna as perguntas com base no índice do módulo
        return when (moduloIndex) {
            0 -> listaDePerguntasDoModulo1()
            1 -> listaDePerguntasDoModulo2()
            // Adicione os outros módulos
            else -> emptyList()
        }
    }

    // Funções para obter listas de perguntas de cada módulo
}
