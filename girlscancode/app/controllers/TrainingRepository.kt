package girlscancode.app.controllers


import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import girlscancode.app.models.Training

class TrainingRepository( database: FirebaseFirestore){
    private val db = database

    companion object {
        private lateinit var instance: TrainingRepository
        private lateinit var trainingCollection: HashMap<String, Any>
        private lateinit var dbCollection: String
        //private lateinit var listTraining: ArrayList<Map<String, Any>?>

        fun getInstance(database: FirebaseFirestore): TrainingRepository{
            if(!this::instance.isInitialized){
                this.instance = TrainingRepository(database)
                this.trainingCollection = HashMap()
                //this.listTraining = ArrayList()
                this.dbCollection = database.collection("Training").id
            }
            return this.instance
        }
    }

    fun addTraining(training: Training){
        trainingCollection["nameTraining"] = training.getName()
        trainingCollection["beginningDate"] = training.getBeginningDate()
        trainingCollection["finalDate"] = training.getFinalDate()
        trainingCollection["adressTraining"] = training.getAdress()
        trainingCollection["siteLink"] = training.getSiteLink()
        trainingCollection["descriptionTraining"] = training.getDescription() ?: ""



       db.collection(dbCollection).document().set(trainingCollection)
            .addOnSuccessListener {_ ->
                Log.d("db", "Curso salvo com sucesso!")
            }.addOnFailureListener {
                   _ ->
            }
    }

    fun getDBCollection(): String{
        return dbCollection
    }




}