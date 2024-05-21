package girlscancode.app.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import girlscancode.app.controllers.EventFactory
import girlscancode.app.controllers.EventRepository
import girlscancode.app.databinding.ActivityAdminEditEventBinding
import girlscancode.app.models.Event
import java.io.InputStream

class AdminEditEventActivity : AppCompatActivity() {
    // Atributes
        private val binding by lazy{ ActivityAdminEditEventBinding.inflate(layoutInflater) }
        private val db = FirebaseFirestore.getInstance()
        private val eF = EventFactory.getInstance()
        private val eR = EventRepository.getInstance(this.db)
        private lateinit var event: Event
        private lateinit var listCond: Array<Boolean>
        private lateinit var userProps: Array<String?>
        /*
            By creating a reference to a file, your app gains access to it
            These references can then be used to upload or download data, get or update metadata or delete the file
            A reference can either point to a specific file or to a higher level node in the hierarchy
        */
        private lateinit var storage: FirebaseStorage
        private lateinit var storageRef: StorageReference
        private lateinit var file: InputStream

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        this.storage = Firebase.storage
        this.file = assets.open("imagem.jpg")
        this.storageRef = this.storage.reference.child("Imagens/imagem.jpg")

        this.userProps = arrayOf(intent.extras?.getString("firstName"))
    }

    override fun onResume() {
        super.onResume()

        this.binding.imageButton.setOnClickListener{_ ->
            startActivity(Intent(this, MenuAdminActivity::class.java)
                .putExtra(
                    "firstName",
                    this.userProps[0]
                )
            )
            this.finish()
        }

        this.binding.button.setOnClickListener{_ ->
            // https://developer.android.com/training/data-storage/shared/documents-files
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT

            startActivityForResult(Intent.createChooser(intent, ""), 200)
            try {
                this.storageRef.putStream(this.file)
            } catch (e: Exception) {
                Toast.makeText(this, "Line 70", Toast.LENGTH_SHORT).show()
            }
        }

        this.binding.PostButton.setOnClickListener {
            this.listCond = arrayOf(
                this.binding.NomeEvento.text.toString().isEmpty() ||
                        this.binding.DataInicio.text.toString().isEmpty() ||
                        this.binding.DataFim.text.toString().isEmpty() ||
                        this.binding.morada.text.toString().isEmpty() ||
                        this.binding.linkSite.text.toString().isEmpty(),
            )

            var isCond = true

            for (cond in this.listCond) {
                if (cond) {
                    Toast.makeText(this, "Inválido : campo(s) por preencher", Toast.LENGTH_SHORT).show()
                    isCond = false
                }
            }

            if (isCond) {
                this.event = this.eF.createEvent(
                    this.binding.NomeEvento.text.toString(),
                    this.binding.DescricaoEvento.text.toString(),
                    this.binding.DataInicio.text.toString(),
                    this.binding.DataFim.text.toString(),
                    this.binding.morada.text.toString(),
                    this.binding.linkSite.text.toString(),
                )

                this.eR.addEvent(this.event)

                startActivity(Intent(this, PostSuccessAdmin::class.java)
                    .putExtra(
                        "firstName",
                        this.userProps[0]
                    )
                )
                this.finish()
            }
        }
    }

    /*
    override fun onActivityResult(reqCode: Int, resCode: Int, data: Intent ) {
        super.onActivityResult(11,
            11,
            null)

        if (reqCode == 100 && resCode == RESULT_OK) {
           this.binding
        }
    }
     */
}
