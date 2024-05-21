package girlscancode.app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import girlscancode.app.databinding.ActivityEmailSentForgotPassBinding

class EmailSentForgotPass : AppCompatActivity() {
    val binding by lazy{ ActivityEmailSentForgotPassBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this, LoginActivity::class.java))
                this.finish()
            },3000
        )
    }
}