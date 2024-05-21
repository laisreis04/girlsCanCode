package girlscancode.app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import girlscancode.app.databinding.ActivityUpdateSuccessBinding

class UpdateSuccessActivity : AppCompatActivity() {
    private val binding by lazy{ActivityUpdateSuccessBinding.inflate(layoutInflater)}
    private lateinit var userProps: Array<String?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        this.userProps = arrayOf(intent.extras?.getString("firstName"))
    }

    override fun onStart() {
        super.onStart()

        Handler(Looper.getMainLooper()).postDelayed(
            { startActivity(Intent(this, MenuPrincipalUserActivity::class.java)
                .putExtra(
                    "firstName",
                    this.userProps[0]
                ))
                finish()
            },3000
        )
    }
}