package girlscancode.app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import girlscancode.app.controllers.UserRepository
import girlscancode.app.databinding.ActivitySplashScreenBinding
import girlscancode.app.models.User
import java.lang.Exception
import java.time.LocalDateTime

class SplashScreen : AppCompatActivity() {
    private val binding by lazy { ActivitySplashScreenBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(this.binding.root)



//        lateinit var userCollection: HashMap<String, Any>
//
//        val userListdata: ArrayList<User> = arrayListOf()
//        try {
//            FirebaseFirestore.getInstance().collection("Users").get().addOnSuccessListener { docs ->
//                for (userId in docs.documents) {
//                    Log.d("VerificarID", "${userId}")
//                    for (dataUser in userId.data?.entries!!) {
//                        when(dataUser.key){
//                            "firstName" -> {
//                                userCollection["firstName"] = dataUser.value}
//                            "lastName" -> userCollection["lastName"] = dataUser.value
//                            "email" ->  userCollection["email"] = dataUser.value
//                            "password" ->  userCollection["password"] = dataUser.value
//                            "typeUser" ->   userCollection["typeUser"] = dataUser.value
//                            "createdAt" ->   userCollection["createdAt"] = dataUser.value
//                            "emailVerified" ->    userCollection["emailVerified"] = dataUser.value
//                            "updatedAt" ->   userCollection["updatedAt"] = dataUser.value
//                            "firstLoginAt" ->    userCollection["firstLoginAt"] = dataUser.value
//                        }
//                    }
//                    userListdata.add(
//                        User(
//                            userCollection["firstName"].toString(),
//                            userCollection["lastName"].toString(),
//                            userCollection["email"].toString(),
//                            userCollection["password"].toString(),
//                            userCollection["typeUser"].toString(),
//                            LocalDateTime.now(),true,
//                            LocalDateTime.now(),
//                            LocalDateTime.now())
//                    )
//                }
//            }
//        }
//        catch (e: Exception){
//            Toast.makeText(this, "Funcionou", Toast.LENGTH_SHORT).show()
//        }



//        Log.d("listinhasplash", "$userListdata")
    }

    override fun onStart() {
        super.onStart()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        },3000)
    }

}
