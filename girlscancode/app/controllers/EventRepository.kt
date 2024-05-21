package girlscancode.app.controllers

import com.google.firebase.firestore.FirebaseFirestore
import girlscancode.app.models.Event

class EventRepository(dataBase: FirebaseFirestore) {
    // Attributes
        private val db = dataBase

        companion object {
            // Attributes
                private lateinit var instance: EventRepository
                private lateinit var eventCollection: HashMap<String, Any>
                private lateinit var dbCollection: String

            // Methods
                fun getInstance(dataBase: FirebaseFirestore): EventRepository {
                    if (!this::instance.isInitialized) {
                        this.instance = EventRepository(dataBase)
                        this.eventCollection = HashMap()
                        this.dbCollection = dataBase.collection("Events").id
                    }
                    return this.instance
                }
        }

    fun addEvent(event: Event) {
        eventCollection["name"] = event.getName()
        eventCollection["description"] = event.getDescription() ?: ""
        eventCollection["startDate"] = event.getStartDate()
        eventCollection["endDate"] = event.getEndDate()
        eventCollection["address"] = event.getAddress()
        eventCollection["link"] = event.getLink()
        eventCollection["createdAt"] = event.getCreatedAt()

        db.collection(dbCollection).document().set(eventCollection)
            .addOnSuccessListener { _ ->

            }.addOnFailureListener{ _ ->
            }
    }

    fun getDbCollection(): String {
        return dbCollection
    }
}
