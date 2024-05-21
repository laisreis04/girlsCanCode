package girlscancode.app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import girlscancode.app.databinding.ActivityMenuPrincipalUserBinding

class MenuPrincipalUserActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMenuPrincipalUserBinding.inflate(layoutInflater) }
    private lateinit var userProps: Array<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(this.binding.root)

        this.userProps = arrayOf(intent.extras?.getString("firstName"), intent.extras?.getString("typeUser"))
    }

    override fun onStart() {
        super.onStart()

        this.binding.textHelloUser.text = "Olá ${this.userProps[0]}"
    }

    override fun onResume() {
        super.onResume()

        this.binding.cardEditEvent.setOnClickListener { _ ->
            startActivity(Intent(this, EventMainActivity::class.java)
                .putExtra(
                    "firstName",
                    this.userProps[0]
                )
            )
            this.finish()
        }
        this.binding.cardEditTraining.setOnClickListener { _ ->
            startActivity(Intent(this, TrainingUserMainActivity::class.java)
                .putExtra(
                    "firstName",
                    this.userProps[0]
                )
            )
            this.finish()
        }
        this.binding.cardAddAdmin.setOnClickListener { _ ->
            startActivity(Intent(this, ProfileSettingsActivity::class.java)
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
        this.binding.cardCommunity.setOnClickListener { _ ->
            startActivity(Intent(this, CommunityActivity::class.java)
                .putExtra(
                    "firstName",
                    this.userProps[0]
                )
            )
        }
    }
}