package girlscancode.app.models

import java.time.LocalDateTime

data class Workshop(
    private var name: String,
    private var description: String,
    private var startDate: LocalDateTime,
    private var endDate: LocalDateTime,
    private var address: String?,
    private var city: String?,
    private var country: String?,
    private var phoneNumber: Int?,
    private var email: String?,
    private var link: String?,
    private var maxCapacity: Int?,
    private var createdAt: LocalDateTime,
    private var updatedAt: LocalDateTime?
) {
    // Getters
        fun getName(): String {
            return this.name
        }
        fun getDescription(): String {
            return this.description
        }
        fun getStartDate(): LocalDateTime {
            return this.startDate
        }
        fun getEndDate(): LocalDateTime {
            return this.endDate
        }
        fun getAddress(): String? {
            return this.address
        }
        fun getCity(): String? {
            return this.city
        }
        fun getCountry(): String? {
            return this.country
        }
        fun getPhoneNumber(): Int? {
            return this.phoneNumber
        }
        fun getEmail(): String? {
            return this.email
        }
        fun getLink(): String? {
            return this.link
        }
        fun getMaxCapacity(): Int? {
            return this.maxCapacity
        }
        fun getCreatedAt(): LocalDateTime {
            return this.createdAt
        }
        fun getUpdatedAt(): LocalDateTime? {
            return this.updatedAt
        }
    // Setters
        fun setName(str: String) {
            this.name = str
        }
        fun setDescription(str: String) {
            this.description = str
        }
        fun setStartDate(date: LocalDateTime) {
            this.startDate = date
        }
        fun setEndDate(date: LocalDateTime) {
            this.endDate = date
        }
        fun setAddress(str: String) {
            this.address = str
        }
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
        fun setLink(str: String) {
            this.link = str
        }
        fun setMaxCapacity(num: Int) {
            this.maxCapacity = num
        }
        fun setCreatedAt(date: LocalDateTime) {
            this.createdAt = date
        }
        fun setUpdatedAt(date: LocalDateTime) {
            this.updatedAt = date
        }
}
