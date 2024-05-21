package girlscancode.app.controllers

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import girlscancode.app.models.Workshop

class WorkshopRepository {
    companion object {
        private lateinit var instance: WorkshopRepository
        // Access a Cloud Firestore instance
            // The instance is created using the getInstance() method of the FirebaseAuth : singleton
                private lateinit var db: FirebaseFirestore
            // The instance is created by using an extension property of the Firebase class
                //private lateinit var db2: FirebaseFirestore
        private lateinit var workshopCollection: HashMap<String, Any>
        private lateinit var dbCollection: ArrayList<String>

        fun getInstance(): WorkshopRepository {
            if (!this::instance.isInitialized) {
                this.instance = WorkshopRepository()
                this.db = FirebaseFirestore.getInstance()
                //this.db2 = Firebase.firestore
                this.workshopCollection = HashMap()
                this.dbCollection = ArrayList()
                this.dbCollection.add("Workshops")
                this.dbCollection.add("1")
            }
            return this.instance
        }
    }

    fun addWorkshop(workshop: Workshop) {
        workshopCollection["name"] = workshop.getName()
        workshopCollection["description"] = workshop.getDescription()
        workshopCollection["startDate"] = workshop.getStartDate()
        workshopCollection["endDate"] = workshop.getEndDate()
        workshopCollection["address"] = workshop.getAddress() ?: ""
        workshopCollection["city"] = workshop.getCity() ?: ""
        workshopCollection["country"] = workshop.getCountry() ?: ""
        workshopCollection["phoneNumber"] = workshop.getPhoneNumber() ?: 0
        workshopCollection["email"] = workshop.getEmail() ?: ""
        workshopCollection["link"] = workshop.getLink() ?: ""
        workshopCollection["maxCapacity"] = workshop.getMaxCapacity() ?: 0
        workshopCollection["createdAt"] = workshop.getCreatedAt()
        workshopCollection["updatedAt"] = workshop.getUpdatedAt() ?: ""

        db.collection(dbCollection[0]).document(dbCollection[1]).set(workshopCollection)
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