import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Exemplo de lista de módulos
        val modulos = listOf(
            Modulo("Módulo 1", listaDePerguntasDoModulo1()),
            Modulo("Módulo 2", listaDePerguntasDoModulo2())
            // Adicione os outros módulos
        )

        // Criar botões para cada módulo
        modulos.forEachIndexed { index, modulo ->
            val button = Button(this).apply {
                text = modulo.titulo
                setOnClickListener {
                    val intent = Intent(this@MainActivity, QuizActivity::class.java)
                    intent.putExtra("moduloIndex", index)
                    startActivity(intent)
                }
            }
            // Adicione o botão ao layout (exemplo simplificado)
        }
    }

    // Exemplo de lista de perguntas para o módulo 1
    private fun listaDePerguntasDoModulo1(): List<Questao> {
        return listOf(
            Questao("Como se diz 'Olá' em inglês?", listOf("Hello", "Goodbye", "Thanks", "Hi"), 0),
            Questao("Qual o plural de 'child'?", listOf("Children", "Childs", "Childes", "Child"), 0)
            // Outras perguntas
        )
    }

    // Adicione mais funções para os outros módulos
}
