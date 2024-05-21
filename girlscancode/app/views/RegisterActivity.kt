package girlscancode.app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import girlscancode.app.controllers.UserFactory
import girlscancode.app.controllers.UserRepository
import girlscancode.app.databinding.ActivityRegisterBinding
import girlscancode.app.enums.TypeUser
import girlscancode.app.models.User

class RegisterActivity : AppCompatActivity() {
    // Atributes
    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val db = FirebaseFirestore.getInstance()
    private val uF = UserFactory.getInstance()
    private val uR = UserRepository.getInstance(this.db)
    private lateinit var user: User
    private lateinit var listCond: Array<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(this.binding.root)
    }

    override fun onResume() {
        super.onResume()

        this.binding.btnRegister.setOnClickListener { _ ->
            this.listCond = arrayOf(
                this.binding.inputFirstName.text.isNullOrEmpty() ||
                        this.binding.inputLastName.text.isNullOrEmpty() ||
                        this.binding.editTextTextEmailAddress.text.isNullOrEmpty() ||
                        this.binding.editTextTextPassword.text.isNullOrEmpty() ||
                        this.binding.editTextTextPassword2.text.isNullOrEmpty(),
                this.binding.editTextTextPassword.text.toString() != this.binding.editTextTextPassword2.text.toString(),
                this.binding.editTextTextPassword.text.length < 6
            )

            if (this.listCond[0]) {
                Toast.makeText(this, "Inválido : campo(s) por preencher", Toast.LENGTH_SHORT).show()
            } else if (this.listCond[1]) {
                Toast.makeText(this, "Inválido : senha não confirmada", Toast.LENGTH_SHORT).show()
            } else if (this.listCond[2]) {
                Toast.makeText(this, "Inválido : senha inválida", Toast.LENGTH_SHORT).show()
            }

            if (!this.listCond[0] && !this.listCond[1] && !this.listCond[2]) {
                this.user = this.uF.createUser(
                    this.binding.inputFirstName.text.toString(),
                    this.binding.inputLastName.text.toString(),
                    this.binding.editTextTextEmailAddress.text.toString(),
                    this.binding.editTextTextPassword.text.toString(),
                    TypeUser.USER,
                )
            }
            try {
                this.auth.createUserWithEmailAndPassword(
                    this.binding.editTextTextEmailAddress.text.toString(),
                    this.binding.editTextTextPassword.text.toString()
                ).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        this.uR.addUser(this.user, this.auth.currentUser?.uid)
                        this.auth.currentUser?.sendEmailVerification()
                        Toast.makeText(this, "Credenciais criadas e email enviado", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, ConfirmationCreateAccount::class.java))
                        finish()
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