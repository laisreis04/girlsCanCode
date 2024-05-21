package girlscancode.app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import girlscancode.app.databinding.ActivityPostSuccessAdminBinding

class PostSuccessAdmin : AppCompatActivity() {
    private val binding by lazy{ ActivityPostSuccessAdminBinding.inflate(layoutInflater) }
    private lateinit var userProps: Array<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        this.userProps = arrayOf(intent.extras?.getString("firstName"))
    }

    override fun onStart() {
        super.onStart()

        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this, MenuAdminActivity::class.java)
                    .putExtra(
                        "firstName",
                        this.userProps[0]
                    )
                )
                this.finish()
            },3000
        )
    }
}