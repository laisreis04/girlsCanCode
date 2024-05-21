package girlscancode.app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import girlscancode.app.controllers.UserFactory
import girlscancode.app.controllers.UserRepository
import girlscancode.app.databinding.ActivitySettingsAdminBinding
import girlscancode.app.enums.TypeUser
import girlscancode.app.models.User

class SettingsAdminActivity : AppCompatActivity() {
    // Atributes
        private val binding by lazy{ ActivitySettingsAdminBinding.inflate(layoutInflater) }
        private val auth by lazy { FirebaseAuth.getInstance() }
        private val db = FirebaseFirestore.getInstance()
        private val uR = UserRepository.getInstance(this.db)
        private val uF = UserFactory.getInstance()
        private lateinit var listCond: Array<Boolean>
        private lateinit var user: User
        private lateinit var userProps: Array<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        this.userProps = arrayOf(intent.extras?.getString("firstName"), intent.extras?.getString("typeUser"))
    }

    override fun onStart() {
        super.onStart()

        this.binding.textHelloAdmin.text = "Olá ${this.userProps[0]}"
    }

    override fun onResume() {
        super.onResume()

        this.binding.iconHome.setOnClickListener{ _ ->
            startActivity(Intent(this, MenuAdminActivity::class.java)
                .putExtra(
                    "firstName",
                    this.userProps[0]
                )
            )
            this.finish()
        }

        this.binding.btnSeeAsUser.setOnClickListener{ _ ->
            startActivity(Intent(this, MenuPrincipalUserActivity::class.java)
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

        this.binding.btnAddAdmin.setOnClickListener{ _ ->
            this.listCond = arrayOf(
                this.binding.inputFirstNameAdmin.text.isNullOrEmpty() ||
                        this.binding.inputEmailAdmin.text.isNullOrEmpty() ||
                        this.binding.editTextTextPassword3.text.isNullOrEmpty() ||
                        this.binding.editTextTextPassword4.text.isNullOrEmpty(),
                this.binding.editTextTextPassword3.text.toString() != this.binding.editTextTextPassword4.text.toString(),
                this.binding.editTextTextPassword3.text.length < 6
            )

            if (this.listCond[0]) {
                Toast.makeText(this, "Inválido : campo(s) por preencher", Toast.LENGTH_SHORT).show()
            } else if (this.listCond[1]) {
                Toast.makeText(this, "Inválido : senha não confirmada", Toast.LENGTH_SHORT).show()
            } else if (this.listCond[2]) {
                Toast.makeText(this, "Inválido : senha inválida", Toast.LENGTH_SHORT).show()
            }

            if (!this.listCond[0] && !this.listCond[1] && !this.listCond[2]) {
                try {
                    this.auth.createUserWithEmailAndPassword(
                        this.binding.inputEmailAdmin.text.toString(),
                        this.binding.editTextTextPassword3.text.toString()
                    ).addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            this.user = this.uF.createUser(
                                this.binding.inputFirstNameAdmin.text.toString(),
                                "",
                                this.binding.inputEmailAdmin.text.toString(),
                                this.binding.editTextTextPassword3.text.toString(),
                                TypeUser.ADMIN
                            )
                            this.uR.addUser(this.user, this.auth.currentUser!!.uid)
                            this.auth.currentUser?.sendEmailVerification()
                            Toast.makeText(this, "Credenciais criadas e email enviado", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, ConfirmationCreateAccount::class.java))
                            this.finish()
                        } else {
                            Toast.makeText(this, "Credenciais não foram criadas", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Credenciais já existentes", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}