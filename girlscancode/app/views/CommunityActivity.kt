package girlscancode.app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import girlscancode.app.R
import girlscancode.app.controllers.CommunityListAdapter
import girlscancode.app.controllers.UserRepository
import girlscancode.app.databinding.ActivityCommunityBinding
import girlscancode.app.models.Comunidade
import girlscancode.app.models.fakeCommunity

class CommunityActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCommunityBinding.inflate(layoutInflater) }
    private lateinit var userProps: Array<String?>
    private val db = FirebaseFirestore.getInstance()
    private val uR = UserRepository.getInstance(this.db)
    private lateinit var userFields : ArrayList<String>
    private lateinit var listCom : MutableList<Comunidade>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        this.userProps = arrayOf(intent.extras?.getString("firstName"), intent.extras?.getString("typeUser"))
        this.userFields = arrayListOf()
        this.listCom = mutableListOf()

        db.collection(uR.getDbCollection()).get()
            .addOnSuccessListener { doc ->
                for (item in doc.documents) {
                    for (item2 in item.data?.entries!!) {
                            when(item2.key.toString()) {
                                "firstName" -> { this.userFields.add(item2.value.toString()) }
                                "description" -> { this.userFields.add(item2.value.toString()) }
                                "keywords" -> { this.userFields.add(item2.value.toString()) }
                            }
                        //Comunidade(this.userFields[0], this.userFields[0], this.userFields[0])
                        //this.listCom.add(Comunidade(this.userFields.get(0), this.userFields.get(0), this.userFields.get(0)))
                    }
                }
            }

        //fakeCommunity()

        try {
            val rvCommunityView = findViewById<RecyclerView>(R.id.recyclerview_community)
            rvCommunityView.adapter = CommunityListAdapter(fakeCommunity())
            rvCommunityView.layoutManager = LinearLayoutManager(this)
        } catch (e: Exception) {
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()

        this.binding.btnBackHome.setOnClickListener{ _ ->
            startActivity(
                Intent(this, MenuPrincipalUserActivity::class.java)
                    .putExtra(
                        "firstName",
                        this.userProps[0]
                    )
                    .putExtra(
                        "typeUser",
                        this.userProps[1]
                    )
            )
            this.finish()
        }
    }
}
