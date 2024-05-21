package girlscancode.app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import girlscancode.app.R
import girlscancode.app.controllers.TrainingUserAdapter
import girlscancode.app.databinding.ActivityTrainingUserMainBinding
import girlscancode.app.models.fakeFormacao

class TrainingUserMainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityTrainingUserMainBinding.inflate(layoutInflater) }
    private lateinit var userProps: Array<String?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        this.userProps = arrayOf(intent.extras?.getString("firstName"), intent.extras?.getString("typeUser"))

        val rvTrainingView = findViewById<RecyclerView>(R.id.rv_training_main)

        rvTrainingView.adapter = TrainingUserAdapter(fakeFormacao())
        rvTrainingView.layoutManager = LinearLayoutManager(this)




    }

    override fun onResume() {
        super.onResume()

        this.binding.homeBtn.setOnClickListener{ _ ->
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
    }

}