package girlscancode.app.models

class Training(
    private var nameTraining: String,
    private var beginningDate: String,
    private var finalDate: String,
    private var addressTraining: String,
    private var siteLink: String,
    private var descriptionTraining: String?
) {

    constructor() : this("", "", "", "", "", null)

    fun getName(): String {
        return this.nameTraining
    }

    fun getBeginningDate(): String{
        return this.beginningDate
    }
    fun getFinalDate(): String{
        return this.finalDate
    }

    fun getAdress(): String{
        return this.addressTraining
    }

    fun getSiteLink(): String{
        return this.siteLink
    }

    fun getDescription(): String?{
        return this.descriptionTraining
    }

    //Implantar set

    fun setName(name: String) {
        this.nameTraining = name
    }

    fun setBeginningDate(date: String){
        this.beginningDate = date
    }
    fun setFinalDate(date: String){
        this.beginningDate = date
    }

    fun setAdress(andress: String){
       this.addressTraining = andress
    }

    fun setSiteLink(site: String){
        this.siteLink = site
    }

    fun setDescription( description: String){
        this.descriptionTraining = description
    }


}

