package girlscancode.app.controllers

import girlscancode.app.models.Training

class TrainingFactory {

    companion object{
        private lateinit var instance: TrainingFactory

        fun getInstance(): TrainingFactory{
            if(!this::instance.isInitialized){
                this.instance = TrainingFactory()
            }
            return this.instance
        }
    }

    fun createTraining(nameTraining : String, beginningDate : String, finalDate : String, addressTraining : String, siteLink: String, descriptionTraining: String) : Training{
        return Training(nameTraining, beginningDate, finalDate, addressTraining, siteLink, descriptionTraining)
    }
}