package girlscancode.app.controllers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import girlscancode.app.R
import girlscancode.app.models.Formacao
import girlscancode.app.models.formacao

class TrainingUserAdapter(val treinamentos: MutableList<Formacao>) : RecyclerView.Adapter<TrainingUserAdapter.TrainingViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrainingUserAdapter.TrainingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_training_item, parent, false)
        return TrainingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrainingUserAdapter.TrainingViewHolder, position: Int) {
        holder.bind(treinamentos[position])
    }

    override fun getItemCount(): Int = treinamentos.size

    inner class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(treinamento: Formacao){
            val textNomeEvento = itemView.findViewById<TextView>(R.id.textNomeFormacao)
            val dataInicio = itemView.findViewById<TextView>(R.id.textDataInicio)
            val dataFim = itemView.findViewById<TextView>(R.id.textDataFim)
            val local = itemView.findViewById<TextView>(R.id.localText)

            // Atribuir os valores corretos aos TextViews

            textNomeEvento.text = treinamento.nome // Supondo que 'nome' seja uma String
            dataInicio.text = treinamento.dataInicio // Supondo que 'dataInicio' seja uma String
            dataFim.text = treinamento.dataFim // Supondo que 'dataFim' seja uma String
            local.text = treinamento.local

        }

    }

}