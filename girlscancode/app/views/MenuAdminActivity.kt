package girlscancode.app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import girlscancode.app.databinding.ActivityMenuAdminBinding

class MenuAdminActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityMenuAdminBinding.inflate(layoutInflater) }
    private lateinit var userProps: Array<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        this.userProps = arrayOf(intent.extras?.getString("firstName"), intent.extras?.getString("typeUser"))
    }

    override fun onStart() {
        super.onStart()

        this.binding.textHelloUser.text = "Olá ${this.userProps[0]}"
    }

    override fun onResume() {
        super.onResume()

        this.binding.cardAddAdmin.setOnClickListener{ _ ->
            startActivity(Intent(this, SettingsAdminActivity::class.java)
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
        this.binding.cardEditEvent .setOnClickListener{ _ ->
            startActivity(Intent(this, AdminEditEventActivity::class.java)
                .putExtra(
                    "firstName",
                    this.userProps[0]
                )
            )
            this.finish()
        }
        this.binding.cardEditTraining.setOnClickListener{ _ ->
            startActivity(Intent(this, AdminTrainingEditViewActivity::class.java)
                .putExtra(
                    "firstName",
                    this.userProps[0]
                )
            )
            this.finish()
        }
    }
}