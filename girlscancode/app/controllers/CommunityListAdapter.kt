package girlscancode.app.controllers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import girlscancode.app.R
import girlscancode.app.models.Comunidade

class CommunityListAdapter(val comunidades: MutableList<Comunidade>) : RecyclerView.Adapter<CommunityListAdapter.CommunityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_communuty_list, parent, false)

        return CommunityViewHolder(view)
    }

    override fun getItemCount(): Int = comunidades.size

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        holder.bind(comunidades[position])
    }

    inner class CommunityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comunidade: Comunidade) {
            with(comunidade) {
                var firstName: TextView = itemView.findViewById(R.id.textFirstName)
                var descriptionText: TextView = itemView.findViewById(R.id.textDescription)
                var keywordS: TextView = itemView.findViewById(R.id.textKeyword)

                firstName.text = comunidade.firstName
                descriptionText.text = comunidade.description
                keywordS.text = comunidade.keywords

                /*
                val db = FirebaseFirestore.getInstance()
                val uR = UserRepository.getInstance(db)

                db.collection(uR.getDbCollection()).get()
                    .addOnSuccessListener { doc ->
                        for (item in doc.documents) {
                            for (item2 in item.data?.entries!!) {
                                when(item2.key) {
                                    "firstName" -> { firstName.text = item2.value.toString()}
                                    "description" -> { descriptionText.text = item2.value.toString()}
                                    "keywords" -> { keywordS.text = item2.value.toString()}
                                }
                            }
                        }
                    }
                 */
            }
        }
    }
}
