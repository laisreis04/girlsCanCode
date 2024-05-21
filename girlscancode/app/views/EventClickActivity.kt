package girlscancode.app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatTextView
import girlscancode.app.R

class EventClickActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_click)

        val voltar = findViewById<AppCompatTextView>(R.id.voltarClick)
        val buttonHome = findViewById<ImageButton>(R.id.imageButton)

        buttonHome.setOnClickListener{
            val intent = Intent(this, MenuPrincipalUserActivity::class.java)
            startActivity(intent)
            finish()
        }

        voltar.setOnClickListener{
            val intent = Intent(this, EventMainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}