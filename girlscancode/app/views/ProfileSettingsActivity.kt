package girlscancode.app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import girlscancode.app.controllers.UserRepository
import girlscancode.app.databinding.ActivityProfileSettingsBinding
import java.time.LocalDateTime

class ProfileSettingsActivity : AppCompatActivity() {
    private val binding by lazy { ActivityProfileSettingsBinding.inflate(layoutInflater) }
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val db = FirebaseFirestore.getInstance()
    private val uR = UserRepository.getInstance(this.db)
    private lateinit var userProps: Array<String?>
    private lateinit var listCond: Array<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        this.userProps = arrayOf(intent.extras?.getString("firstName"), intent.extras?.getString("typeUser"))

        this.listCond = arrayOf(
            this.binding.inputName.text.toString().isEmpty(),
            this.binding.inputLastNameProfile.text.toString().isEmpty(),
            this.binding.inputBirthDate.text.toString().isEmpty(),
            this.binding.inputAddress.text.toString().isEmpty(),
            this.binding.radioGroupCountry.checkedRadioButtonId.toString().isEmpty(),
            this.binding.radioGroup2.checkedRadioButtonId.toString().isEmpty(),
            this.binding.inputAboutMe.text.toString().isEmpty(),
            this.binding.inputKeywords.text.toString().isEmpty(),
        )
    }

    override fun onStart() {
        super.onStart()

        this.binding.textNameUser.text = "Olá, ${this.userProps[0]}"

        this.db.collection(this.uR.getDbCollection()).document(this.auth.currentUser?.uid!!).get()
            .addOnSuccessListener { doc ->
                for (item in doc.data?.entries!!) {
                    when(item.key) {
                        "typeUser" -> {
                            if (item.value == "USER") {
                                this.binding.btnBackAdmin.isVisible = false
                            }
                            if (item.value == "ADMIN") {
                                this.binding.btnBackAdmin.isVisible = true
                            }
                        }
                    }
                }
            }
            .addOnFailureListener(this){ _ ->

            }

        this.db.collection(this.uR.getDbCollection())
            .document(this.auth.currentUser?.uid!!).get()
            .addOnSuccessListener { doc ->
                for (item in doc.data?.entries!!) {
                    when (item.key) {
                        "firstName" -> {
                            this.binding.inputName.setText(item.value.toString())
                        }
                        "lastName" -> {
                            this.binding.inputLastNameProfile.setText(item.value.toString())
                        }
                        "email" -> {
                            this.binding.editTextTextEmailAddress3.setText(item.value.toString())
                        }
                        "birthday" -> {
                            this.binding.inputBirthDate.setText(item.value.toString())
                        }
                        "mobileNumber" -> {
                            this.binding.inputAddress.setText(item.value.toString())
                        }
                        "country" -> {
                            when(item.value) {
                                "Portugal" -> { this.binding.Portugal.performClick() }
                                "Outro" -> { this.binding.radioBtnCountryOther.performClick() }
                            }
                        }
                        "city" -> {
                            when(item.value) {
                                "Aveiro" -> { this.binding.radioAveiro.performClick() }
                                "Beja" -> { this.binding.radioBeja.performClick() }
                                "Braga" -> { this.binding.radioBraga.performClick() }
                                "Bragança" -> { this.binding.radioBraganca.performClick() }
                                "Castelo Branco" -> { this.binding.radioCasteloBranco.performClick() }
                                "Coimbra" -> { this.binding.radioCoimbra.performClick() }
                                "Évora" -> { this.binding.radioEvora.performClick() }
                                "Faro" -> { this.binding.radioFaro.performClick() }
                                "Funchal" -> { this.binding.radioFunchal.performClick() }
                                "Guarda" -> { this.binding.radioGuarda.performClick() }
                                "Leiria" -> { this.binding.radioLeiria.performClick() }
                                "Lisboa" -> { this.binding.radioLisboa.performClick() }
                                "Ponta Delgada" -> { this.binding.radioPontaDelgada.performClick() }
                                "Portalegre" -> { this.binding.radioPortalegre.performClick() }
                                "Porto" -> { this.binding.radioPorto.performClick() }
                                "Santarém" -> { this.binding.radioSantarem.performClick() }
                                "Setúbal" -> { this.binding.radioSetubal.performClick() }
                                "Viana do Castelo" -> { this.binding.radioVianaCastelo.performClick() }
                                "Vila Real" -> { this.binding.radioVilaReal.performClick() }
                                "Viseu" -> { this.binding.radioViseu.performClick() }
                            }
                        }
                        "description" -> {
                            this.binding.inputAboutMe.setText(item.value.toString())
                        }
                        "keywords" -> {
                            this.binding.inputKeywords.setText(item.value.toString())
                        }
                    }
                }
            }
            .addOnFailureListener(this){ _ ->
            }

        this.binding.editTextTextEmailAddress3.isEnabled = false
    }

    override fun onResume() {
        super.onResume()

        this.binding.btnHome.setOnClickListener { _ ->
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

        this.binding.btnUpdate.setOnClickListener { _ ->
            if (!this.listCond[0]) {
                Log.d("Cond0", "Aqui")
                this.db.collection(this.uR.getDbCollection())
                    .document(this.auth.currentUser?.uid!!)
                    .update("firstName", this.binding.inputName.text.toString())
                    .addOnSuccessListener { _ ->
                    }.addOnFailureListener(this) { _ ->
                    }
                this.db.collection(this.uR.getDbCollection())
                    .document(this.auth.currentUser?.uid!!)
                    .update("updatedAt", LocalDateTime.now())
                    .addOnSuccessListener { _ ->
                    }.addOnFailureListener(this) { _ ->
                    }
            }
            if (this.listCond[1]) {
                Log.d("Cond1", "Aqui")
                this.db.collection(this.uR.getDbCollection())
                    .document(this.auth.currentUser?.uid!!)
                    .update("lastName", this.binding.inputLastNameProfile.text.toString())
                    .addOnSuccessListener { _ ->
                    }.addOnFailureListener(this) { _ ->
                    }
                this.db.collection(this.uR.getDbCollection())
                    .document(this.auth.currentUser?.uid!!)
                    .update("updatedAt", LocalDateTime.now())
                    .addOnSuccessListener { _ ->
                    }.addOnFailureListener(this) { _ ->
                    }
            }
            if (this.listCond[2]) {
                Log.d("Cond2", "Aqui")
                this.db.collection(this.uR.getDbCollection())
                    .document(this.auth.currentUser?.uid!!)
                    .update("birthday", this.binding.inputBirthDate.text.toString())
                    .addOnSuccessListener { _ ->
                    }.addOnFailureListener(this) { _ ->
                    }
                this.db.collection(this.uR.getDbCollection())
                    .document(this.auth.currentUser?.uid!!)
                    .update("updatedAt", LocalDateTime.now())
                    .addOnSuccessListener { _ ->
                    }.addOnFailureListener(this) { _ ->
                    }
            }
            if (this.listCond[3]) {
                Log.d("Cond3", "Aqui")
                this.db.collection(this.uR.getDbCollection())
                    .document(this.auth.currentUser?.uid!!)
                    .update("mobileNumber", this.binding.inputAddress.text.toString())
                    .addOnSuccessListener { _ ->
                    }.addOnFailureListener(this) { _ ->
                    }
                this.db.collection(this.uR.getDbCollection())
                    .document(this.auth.currentUser?.uid!!)
                    .update("updatedAt", LocalDateTime.now())
                    .addOnSuccessListener { _ ->
                    }.addOnFailureListener(this) { _ ->
                    }
            }
            if (!this.listCond[4]) {
                Log.d("Cond4", "Aqui")
                this.db.collection(this.uR.getDbCollection())
                    .document(this.auth.currentUser?.uid!!)
                    .update("country", this.binding.radioGroupCountry.findViewById<RadioButton>(this.binding.radioGroupCountry.checkedRadioButtonId).text.toString())
                    .addOnSuccessListener { _ -> }
                    .addOnFailureListener(this) { _ ->
                    }
                this.db.collection(this.uR.getDbCollection())
                    .document(this.auth.currentUser?.uid!!)
                    .update("updatedAt", LocalDateTime.now())
                    .addOnSuccessListener { _ ->
                    }.addOnFailureListener(this) { _ ->
                    }
            }
            if (!this.listCond[5]) {
                Log.d("Cond5", "Aqui")
                this.db.collection(this.uR.getDbCollection())
                    .document(this.auth.currentUser?.uid!!)
                    .update("city", this.binding.radioGroup2.findViewById<RadioButton>(this.binding.radioGroup2.checkedRadioButtonId).text.toString())
                    .addOnSuccessListener { _ -> }
                    .addOnFailureListener(this) { _ ->
                    }
                this.db.collection(this.uR.getDbCollection())
                    .document(this.auth.currentUser?.uid!!)
                    .update("updatedAt", LocalDateTime.now())
                    .addOnSuccessListener { _ ->
                    }.addOnFailureListener(this) { _ ->
                    }
            }
            if (this.listCond[6]) {
                Log.d("Cond6", "Aqui")
                this.db.collection(this.uR.getDbCollection())
                    .document(this.auth.currentUser?.uid!!)
                    .update("description", this.binding.inputAboutMe.text.toString())
                    .addOnSuccessListener { _ ->
                    }.addOnFailureListener(this) { _ ->
                    }
                this.db.collection(this.uR.getDbCollection())
                    .document(this.auth.currentUser?.uid!!)
                    .update("updatedAt", LocalDateTime.now())
                    .addOnSuccessListener { _ ->
                    }.addOnFailureListener(this) { _ ->
                    }
            }
            if (this.listCond[7]) {
                Log.d("Cond7", "Aqui")
                this.db.collection(this.uR.getDbCollection())
                    .document(this.auth.currentUser?.uid!!)
                    .update("keywords", this.binding.inputKeywords.text.toString())
                    .addOnSuccessListener { _ ->
                    }.addOnFailureListener(this) { _ ->
                    }
                this.db.collection(this.uR.getDbCollection())
                    .document(this.auth.currentUser?.uid!!)
                    .update("updatedAt", LocalDateTime.now())
                    .addOnSuccessListener { _ ->
                    }.addOnFailureListener(this) { _ ->
                    }
            }

            startActivity(Intent(this, UpdateSuccessActivity::class.java)
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

        this.binding.btnBackAdmin.setOnClickListener{ _ ->
            startActivity(Intent(this, MenuAdminActivity::class.java)
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

        this.binding.btnLogout.setOnClickListener{ _ ->
            if (this.auth.currentUser?.uid != null) {
                Toast.makeText(this, "Sessão terminada", Toast.LENGTH_SHORT).show()
                this.auth.signOut()
                this.finish()
                System.exit(0);
            }
        }
    }
}
