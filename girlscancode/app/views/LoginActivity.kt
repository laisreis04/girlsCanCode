package girlscancode.app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import girlscancode.app.controllers.UserRepository
import girlscancode.app.databinding.ActivityLoginBinding
import java.time.LocalDateTime
import kotlin.collections.HashMap

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val db = FirebaseFirestore.getInstance()
    private val uR = UserRepository.getInstance(this.db)
    private lateinit var userCollection: HashMap<String, Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(this.binding.root)
        this.userCollection = HashMap()
    }

    override fun onStart() {
        super.onStart()

        if (this.auth.currentUser != null) {
            //Toast.makeText(this, "Login ainda activo", Toast.LENGTH_SHORT).show()
            this.auth.signOut()
        }
    }

    override fun onResume() {
        super.onResume()

        this.binding.botaoLogin.setOnClickListener { _ ->
            try {
                this.auth.signInWithEmailAndPassword(
                    this.binding.inputUserName.text.toString(),
                    this.binding.inputPassword.text.toString()
                ).addOnSuccessListener { _ ->
                    try {
                        if (this.auth.currentUser?.isEmailVerified == true) {
                            this.db.collection(this.uR.getDbCollection())
                                .document(this.auth.currentUser?.uid.toString()).get()
                                .addOnSuccessListener { doc ->
                                    for (pair in doc.data?.entries!!) {
                                        this.userCollection.put(pair.key, pair.value)
                                    }
                                    if (this.userCollection["emailVerified"] == false) {
                                        this.db.collection(this.uR.getDbCollection())
                                            .document(this.binding.inputUserName.text.toString())
                                            .update("emailVerified", true)
                                            .addOnSuccessListener { _ ->
                                                this.userCollection["emailVerified"] = true
                                            }.addOnFailureListener(this) { _ ->
                                            }
                                        this.db.collection(this.uR.getDbCollection())
                                            .document(this.binding.inputUserName.text.toString())
                                            .update("firstLoginAt", LocalDateTime.now())
                                            .addOnSuccessListener { _ ->
                                                this.userCollection["firstLoginAt"] = LocalDateTime.now()
                                            }.addOnFailureListener(this) { _ ->
                                            }
                                        this.db.collection(this.uR.getDbCollection())
                                            .document(this.binding.inputUserName.text.toString())
                                            .update("updatedAt", LocalDateTime.now())
                                            .addOnSuccessListener { _ ->
                                                this.userCollection["updatedAt"] = LocalDateTime.now()
                                            }.addOnFailureListener(this) { _ ->
                                            }
                                    }
                                    if (this.userCollection["typeUser"] == "USER") {
                                        startActivity(
                                            Intent(this, MenuPrincipalUserActivity::class.java)
                                                .putExtra(
                                                    "firstName",
                                                    this.userCollection["firstName"].toString()
                                                )
                                        )
                                    }
                                    if (this.userCollection["typeUser"] == "ADMIN") {
                                        startActivity(
                                            Intent(this, MenuAdminActivity::class.java)
                                                .putExtra(
                                                    "firstName",
                                                    this.userCollection["firstName"].toString()
                                                )
                                                .putExtra(
                                                    "typeUser",
                                                    this.userCollection["typeUser"].toString()
                                                )
                                        )
                                    }
                                    // The method onDestroy() is executed
                                    this.finish()
                                }
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this, "Utilizador não autenticado", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener(this) {
                    Toast.makeText(this, "Login inválido", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Login incompleto", Toast.LENGTH_SHORT).show()
            }
        }

        this.binding.textEsqueceuSenha.setOnClickListener { _ ->
            startActivity(Intent(this, ForgotPassword::class.java))
            // The method onDestroy() is executed
            this.finish()
        }

        this.binding.textCriarNovoRegistro.setOnClickListener { _ ->
            startActivity(Intent(this, RegisterActivity::class.java))
            // The method onDestroy() is executed
            this.finish()
        }
    }
}
