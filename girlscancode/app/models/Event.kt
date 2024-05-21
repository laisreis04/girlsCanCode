package girlscancode.app.models

import java.time.LocalDateTime

data class Event(
    private var name: String,
    private var description: String?,
    private var startDate: String,
    private var endDate: String,
    private var address: String,
    private var link: String,
    private var createdAt: LocalDateTime,
    private var updatedAt: LocalDateTime?
    //private var city: String,
    //private var country: String,
    //private var phoneNumber: Int?,
    //private var email: String?,
    //private var maxCapacity: Int?,
) {
    // Getters
        fun getName(): String {
            return this.name
        }
        fun getDescription(): String? {
            return this.description
        }
        fun getStartDate(): String {
            return this.startDate
        }
        fun getEndDate(): String {
            return this.endDate
        }
        fun getAddress(): String {
            return this.address
        }
        fun getLink(): String {
            return this.link
        }
        fun getCreatedAt(): LocalDateTime {
            return this.createdAt
        }
        fun getUpdatedAt(): LocalDateTime? {
            return this.updatedAt
        }
    /*
        fun getCity(): String {
            return this.city
        }
        fun getCountry(): String {
            return this.country
        }
        fun getPhoneNumber(): Int? {
            return this.phoneNumber
        }
        fun getEmail(): String? {
            return this.email
        }
        fun getMaxCapacity(): Int? {
            return this.maxCapacity
        }
     */
    // Setters
        fun setName(str: String) {
            this.name = str
        }
        fun setDescription(str: String) {
            this.description = str
        }
        fun setStartDate(date: String) {
            this.startDate = date
        }
        fun setEndDate(date: String) {
            this.endDate = date
        }
        fun setAddress(str: String) {
            this.address = str
        }
        fun setLink(str: String) {
            this.link = str
        }
        fun setUpdatedAt(date: LocalDateTime) {
            this.updatedAt = date
        }
        /*
            fun setCity(str: String) {
                this.city = str
            }
            fun setCountry(str: String) {
                this.country = str
            }

            fun setPhoneNumber(num: Int) {
                this.phoneNumber = num
            }
            fun setEmail(str: String) {
                this.email = str
            }
            fun setMaxCapacity(num: Int) {
                this.maxCapacity = num
            }
         */


}
