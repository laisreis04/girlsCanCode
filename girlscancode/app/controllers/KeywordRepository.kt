package girlscancode.app.controllers

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import girlscancode.app.models.Keyword

class KeywordRepository {
    companion object {
        private lateinit var instance: KeywordRepository
        // Access a Cloud Firestore instance
            // The instance is created using the getInstance() method of the FirebaseAuth : singleton
                private lateinit var db: FirebaseFirestore
            // The instance is created by using an extension property of the Firebase class
                //private lateinit var db2: FirebaseFirestore
        private lateinit var keywordCollection: HashMap<String, Any>
        private lateinit var dbCollection: ArrayList<String>

        fun getInstance(): KeywordRepository {
            if (!this::instance.isInitialized) {
                this.instance = KeywordRepository()
                this.db = FirebaseFirestore.getInstance()
                //this.db2 = Firebase.firestore
                this.keywordCollection = HashMap()
                this.dbCollection = ArrayList()
                this.dbCollection.add("Keywords")
                this.dbCollection.add("1")
            }
            return this.instance
        }
    }

    fun addKeyword(keyword: Keyword) {
        keywordCollection["name"] = keyword.getName()
        keywordCollection["createdAt"] = keyword.getCreatedAt()
        keywordCollection["updatedAt"] = keyword.getUpdatedAt() ?: ""

        db.collection(dbCollection[0]).document(dbCollection[1]).set(keywordCollection)
            .addOnSuccessListener {

            }.addOnFailureListener{

            }

        this.setDocumentId()
    }

    fun getAllWorkshops() {
        /*
        db.collection(dbCollection[0]).document(dbCollection[1])
            .get()
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
         */

        Log.d("TESTE", "${db.collection(dbCollection[0]).get()}")
    }

    private fun setDocumentId() {
        var ind = dbCollection[1].toInt()
        ind += 1
        dbCollection[1] = ind.toString()
    }
}