package girlscancode.app.models

data class Evento (
    val imagemEvento: Int ? = null,
    val nome: String,
    val dataInicio: String,
    val dataFim: String,
    val local: String)

    class EventoBuilder{
    var imagemEvento: Int = 0
    var nome: String = ""
    var dataInicio: String = ""
    var dataFim: String = ""
    var local: String =""

    fun build() : Evento = Evento(imagemEvento, nome, dataInicio, dataFim, local)
}

fun event(block: EventoBuilder.() -> Unit): Evento = EventoBuilder().apply(block).build()

fun fakeEvent() = mutableListOf(
    event {
        nome = "Girl Can Code Opening"
        dataInicio = "11/03/2024"
        dataFim = "12/03/2024"
        local = "Cesae Porto, Porto - Portugal"

    },
    event {
        nome = "Geek Girls"
        dataInicio = "25/08/2024"
        dataFim = "25/08/2024"
        local = "Braga Parque"
    },
    event {
        nome = "Tech for women"
        dataInicio = "10/06/2024"
        dataFim = "15/06/2024"
        local = "Aveiro"
    },
    event {
        nome = "Geek Girls"
        dataInicio = "08/09/2024"
        dataFim = "09/09/2024"
        local = "Santa Maria da Feira"
    })