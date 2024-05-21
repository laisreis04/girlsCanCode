package girlscancode.app.controllers

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import girlscancode.app.models.User
import java.time.LocalDateTime
import javax.annotation.meta.When

class UserRepository(dataBase: FirebaseFirestore) {
    // Attributes
    private val db = dataBase

    companion object {
        // Attributes
        private lateinit var instance: UserRepository
        private lateinit var userCollection: HashMap<String, Any>
        private lateinit var dbCollection: String

        // Methods
        fun getInstance(dataBase: FirebaseFirestore): UserRepository {
            if (!this::instance.isInitialized) {
                this.instance = UserRepository(dataBase)
                this.userCollection = HashMap()
                this.dbCollection = dataBase.collection("Users").id
            }
            return this.instance
        }
    }

    // Methods
    fun addUser(user: User, userID: String?) {
        userCollection["firstName"] = user.getFirstName()
        userCollection["lastName"] = user.getLastName()
        userCollection["email"] = user.getEmail()
        userCollection["password"] = user.getPassword()
        userCollection["typeUser"] = user.getTypeUser()
        userCollection["createdAt"] = user.getCreatedAt()
        userCollection["emailVerified"] = user.isEmailVerified()

        this.db.collection(dbCollection).document(userID ?: "").set(userCollection)
            .addOnSuccessListener { _ ->
                //doc ->
            }.addOnFailureListener { _ ->
                //exception -> Log.d("ErroPah", "Get failed with ", exception)
            }
    }

    fun getDbCollection(): String {
        return dbCollection
    }

    fun getAllUsers(): ArrayList<User> {

        val userListdata: ArrayList<User> = arrayListOf()
        this.db.collection(dbCollection).get().addOnSuccessListener { docs ->
            for (userId in docs.documents) {
                Log.d("VerificarID", "${userId}")
                for (dataUser in userId.data?.entries!!) {
                    when(dataUser.key){
                        "firstName" -> {userCollection["firstName"] = dataUser.value}
                        "lastName" -> userCollection["lastName"] = dataUser.value
                        "email" ->  userCollection["email"] = dataUser.value
                        "password" ->  userCollection["password"] = dataUser.value
                        "typeUser" ->   userCollection["typeUser"] = dataUser.value
                        "createdAt" ->   userCollection["createdAt"] = dataUser.value
                        "emailVerified" ->    userCollection["emailVerified"] = dataUser.value
                        "updatedAt" ->   userCollection["updatedAt"] = dataUser.value
                        "firstLoginAt" ->    userCollection["firstLoginAt"] = dataUser.value
                    }
                }
                userListdata.add(User(userCollection["firstName"].toString(),userCollection["lastName"].toString(),userCollection["email"].toString(),userCollection["password"].toString(),userCollection["typeUser"].toString(),
                    LocalDateTime.now(),true,
                    LocalDateTime.now(),
                    LocalDateTime.now()))
            }
        }
        return userListdata
    }
}
