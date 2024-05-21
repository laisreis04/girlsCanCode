package girlscancode.app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import girlscancode.app.databinding.ActivityForgotPasswordBinding

class ForgotPassword : AppCompatActivity() {
    private val binding by lazy { ActivityForgotPasswordBinding.inflate(layoutInflater) }
    private val auth by lazy{ FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        this.binding.btnRecover.setOnClickListener{
            this.auth.sendPasswordResetEmail(binding.inputEmailRecovery.text.toString())
                .addOnSuccessListener {_ ->
                    Toast.makeText(this,"Verifique a sua caixa de correio", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{_ ->
                    Toast.makeText(this,"Erro...", Toast.LENGTH_SHORT).show()
                }
            startActivity(Intent(this, LoginActivity::class.java))
            this.finish()
        }
    }
}
