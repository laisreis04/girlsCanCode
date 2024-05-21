package girlscancode.app.models

data class Comunidade (
    var firstName:String,
    var description:String,
    var keywords:String
)

class ComunidadeBuilder{
    var descricao: String = ""
    var nome: String = ""
    var palavras: String = ""

    fun build() : Comunidade = Comunidade(nome, descricao, palavras)
}

fun community(block: ComunidadeBuilder.() -> Unit): Comunidade = ComunidadeBuilder().apply(block).build()

fun fakeCommunity() = mutableListOf(
    community {
        this.nome = "Maria João Gonçalves"
        this.descricao = "Como fazer se não te enterneces com meus defeitos, ..."
        this.palavras ="Java, Android, Frontend"

    },
    community {
        nome = "Joana Fernandes"
        descricao = "Amar os outros é a única salvação individual que conheço: ninguém estará perdido se der amor e às vezes receber amor em troca."
        palavras ="Paz, Kotlin, PHP"

    },
    community {
        nome = "Lais Reis"
        descricao = "Skate é a arte da vída, anda quem quer, mais é fera quem pode. ..."
        palavras ="C#, Angular, Spring"

    },
    community {
        nome = "Natali Lucas"
        descricao = "Um dia você vai sentar no trono e a verdade será você a fazer"
        palavras ="Java, Angular, PHP"

    })