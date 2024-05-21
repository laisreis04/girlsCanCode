package girlscancode.app.controllers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import girlscancode.app.R
import girlscancode.app.models.Evento

class EventAdapter(val eventos: MutableList<Evento>) :
    RecyclerView.Adapter<EventAdapter.EventoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_event_item, parent, false)
        return EventoViewHolder(view)

    }

    override fun getItemCount(): Int = eventos.size


    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        holder.bind(eventos[position])
    }

    inner class EventoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(evento: Evento) {
            with(evento) {

                val textNomeEvento = itemView.findViewById<TextView>(R.id.textNomeEvento)
                val dataInicio = itemView.findViewById<TextView>(R.id.textDataInicio)
                val dataFim = itemView.findViewById<TextView>(R.id.textDataFim)
                val local = itemView.findViewById<TextView>(R.id.textLocal)

                // Atribuir os valores corretos aos TextViews

                textNomeEvento.text = evento.nome // Supondo que 'nome' seja uma String
                dataInicio.text = evento.dataInicio // Supondo que 'dataInicio' seja uma String
                dataFim.text = evento.dataFim // Supondo que 'dataFim' seja uma String
                local.text = evento.local
            }

        }
    }
}