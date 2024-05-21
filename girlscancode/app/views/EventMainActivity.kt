package girlscancode.app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import girlscancode.app.R
import girlscancode.app.controllers.EventAdapter
import girlscancode.app.databinding.ActivityEventMainBinding
import girlscancode.app.models.fakeEvent

class EventMainActivity : AppCompatActivity() {
    private val binding  by lazy { ActivityEventMainBinding.inflate(layoutInflater) }
    private lateinit var userProps: Array<String?>

    //lateinit var adapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        this.userProps = arrayOf(intent.extras?.getString("firstName"), intent.extras?.getString("typeUser"))

        //val rvEventView = findViewById<RecyclerView>(R.id.rv_event_view)
        //rvEventView.layoutManager = LinearLayoutManager(this)

        val rvEventView = findViewById<RecyclerView>(R.id.rv_event_view)
        //val eventAdapter = EventAdapter(this, rvEventView)
        rvEventView.adapter = EventAdapter(fakeEvent())
        rvEventView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()

        this.binding.imageButton.setOnClickListener{ _ ->
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
    }
}