package girlscancode.app.views

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import girlscancode.app.controllers.TrainingFactory
import girlscancode.app.controllers.TrainingRepository
import girlscancode.app.databinding.ActivityAdminTrainingEditviewBinding
import girlscancode.app.models.Training
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AdminTrainingEditViewActivity : AppCompatActivity() {

    private val binding by lazy{ ActivityAdminTrainingEditviewBinding.inflate(layoutInflater) }
    private val db = FirebaseFirestore.getInstance()
    private val tF = TrainingFactory.getInstance()
    private val tR = TrainingRepository.getInstance(this.db)
    private lateinit var training: Training
    private lateinit var listTraining: Array<Boolean>
    private lateinit var ImageUri: Uri
    private lateinit var storage: FirebaseStorage
    private lateinit var storageRef: StorageReference
    private lateinit var file: InputStream
    private lateinit var userProps: Array<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.binding.root)

        this.userProps = arrayOf(intent.extras?.getString("firstName"))
    }

    override fun onResume() {
        super.onResume()

        this.binding.buttonUploadFoto.setOnClickListener {
            this.storage = Firebase.storage
            this.file = assets.open("imagem.jpg")
            this.storageRef = this.storage.reference.child("Imagens/imagem.jpg")

            this.userProps = arrayOf(intent.extras?.getString("firstName"))
        }

        this.binding.btnHome.setOnClickListener{
            startActivity(Intent(this, MenuAdminActivity::class.java)
                .putExtra(
                    "firstName",
                    this.userProps[0]
                )
            )
        }

        this.binding.ButtonFormacao.setOnClickListener {
            //uploadImage()

            this.listTraining = arrayOf(
                this.binding.NomeFormacao.text.toString().isEmpty() ||
                        this.binding.DataInicio.text.toString().isEmpty() ||
                        this.binding.DataFim.text.toString().isEmpty() ||
                        this.binding.MoradaFormacao.text.toString().isEmpty() ||
                        this.binding.LinkSite.text.toString().isEmpty() ||
                        this.binding.DescricaoFormacao.text.toString().isEmpty()

            )

            var isCond = true

            for (cond in this.listTraining) {
                if (cond) {
                    Toast.makeText(this, "Inválido : campo(s) por preencher", Toast.LENGTH_SHORT).show()
                    isCond = false
                }
            }

            if (isCond) {
                this.training = this.tF.createTraining(
                    this.binding.NomeFormacao.text.toString(),
                    this.binding.DataInicio.text.toString(),
                    this.binding.DataFim.text.toString(),
                    this.binding.MoradaFormacao.text.toString(),
                    this.binding.LinkSite.text.toString(),
                    this.binding.DescricaoFormacao.text.toString(),
                )

                this.tR.addTraining(this.training)

                startActivity(Intent(this, PostSuccessAdmin::class.java)
                    .putExtra(
                        "firstName",
                        this.userProps[0]
                    )
                )
                this.finish()
                limparCampos()
            }
        }
    }

    private fun uploadImage() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading ..")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        //val storageReference = Firebase.getInstance().getReference()

    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 100)
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100 && resultCode == RESULT_OK){
            ImageUri = data?.data!!
            //binding.buttonUploadFoto.setimageURI(ImageUri)

        }
    }*/

    private fun limparCampos(){
        this.binding.NomeFormacao.setText("")
        this.binding.DataInicio.setText("")
        this.binding.DataFim.setText("")
        this.binding.MoradaFormacao.setText("")
        this.binding.LinkSite.setText("")
        this.binding.DescricaoFormacao.setText("")
    }
}