package girlscancode.app.models

data class Formacao(
    val imagemEvento: Int ?=  null,
    val nome: String,
    val dataInicio: String,
    val dataFim: String,
    val local: String
)

class FormacaoBuilder{
    var imagemEvento: Int = 0
    var nome: String = ""
    var dataInicio : String = ""
    var dataFim: String = ""
    var local: String = ""

    fun build(): Formacao = Formacao(imagemEvento, nome, dataInicio, dataFim, local)

}

fun formacao(block: FormacaoBuilder.()-> Unit): Formacao = FormacaoBuilder().apply(block).build()

fun fakeFormacao() = mutableListOf(
    formacao {
        nome = "Analista de Dados"
        dataInicio = "12/03/2024"
        dataFim = "31/06/2024"
        local = "Cesae - Braga"
    },
    formacao {
        nome = "AWS"
        dataInicio = "12/03/2024"
        dataFim = "31/06/2024"
        local = "Cesae - Porto"
    },
    formacao {
        nome = "Manicure"
        dataInicio = "12/03/2024"
        dataFim = "31/06/2024"
        local = "Cesae SMF"
    },
    formacao {
        nome = "Software Developer"
        dataInicio = "12/03/2024"
        dataFim = "31/06/2024"
        local = "Cesae - Aveiro"
    },
    formacao {
        nome = "Analista de Marketing"
        dataInicio = "12/03/2024"
        dataFim = "31/06/2024"
        local = "Cesae - Lisboa"
    },
    formacao {
        nome = "Analista de TI"
        dataInicio = "12/03/2024"
        dataFim = "31/06/2024"
        local = "Cesae - Braga"
    }
)