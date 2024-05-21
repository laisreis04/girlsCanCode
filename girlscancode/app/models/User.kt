package girlscancode.app.models

import java.time.LocalDateTime

data class User(
    // Attributes
        private var firstName: String,
        private var lastName: String,
        private var email: String,
        private var password: String,
        private var typeUser: String,
        private var createdAt: LocalDateTime,
        private var emailVerified: Boolean,
        private var firstLoginAt: LocalDateTime?,
        private var updatedAt: LocalDateTime?
        /*
            private var userName: String,
            private var age: LocalDate?,
            private var city: String?,
            private var country: String?,
            private var address: String?,
            private var phoneNumber: Int,
            private var description: String?,
        */
) {
    // Getters
        fun getFirstName(): String {
            return this.firstName
        }
        fun getLastName(): String {
            return this.lastName
        }
        fun getEmail(): String {
            return this.email
        }
        fun getPassword(): String {
            return this.password
        }
        fun getTypeUser(): String {
            return this.typeUser
        }
        fun getCreatedAt(): LocalDateTime {
            return this.createdAt
        }
        fun isEmailVerified(): Boolean {
            return this.emailVerified
        }
        fun getFirstLoginAt(): LocalDateTime? {
            return this.firstLoginAt
        }
        fun getUpdatedAt(): LocalDateTime? {
            return this.updatedAt
        }
        /*
            fun getUserName(): String {
                return this.userName
            }
            fun getAge(): LocalDate? {
                return this.age
            }
            fun getCity(): String? {
                return this.city
            }
            fun getCountry(): String? {
                return this.country
            }
            fun getAddress(): String? {
                return this.address
            }
            fun getPhoneNumber(): Int {
                return this.phoneNumber
            }
            fun getDescription(): String? {
                return this.description
            }
        */
    // Setters
        fun setFirstName(str: String) {
            this.firstName = str
        }
        fun setLastName(str: String) {
            this.lastName = str
        }
        fun setEmail(str: String) {
            this.email = str
        }
        fun setPassword(str: String) {
            this.password = str
        }
        fun setTypeUser(str: String) {
            this.typeUser = str
        }
        fun setEmailVerified() {
            if (!this.isEmailVerified()) {
                this.emailVerified = true
            }
        }
        fun setFirstLoginAt(date: LocalDateTime) {
            this.firstLoginAt = date
        }
        fun setUpdatedAt(date: LocalDateTime) {
            this.updatedAt = date
        }
        /*
            fun setUserName(str: String) {
                this.userName = str
            }
            fun setAge(num: LocalDate) {
                this.age = num
            }
            fun setCity(str: String) {
                this.city = str
            }
            fun setCountry(str: String) {
                this.country = str
            }
            fun setAddress(str: String) {
                this.address = str
            }
            fun setPhoneNumber(num: Int) {
                this.phoneNumber = num
            }
            fun setDescription(str: String) {
                this.description = str
            }
        */
}
