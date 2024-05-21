package girlscancode.app.controllers

import girlscancode.app.enums.TypeUser
import girlscancode.app.models.User
import java.time.LocalDateTime

class UserFactory {
    // Attributes
        companion object {
            // Attributes
                private lateinit var instance: UserFactory
            // Methods
                fun getInstance(): UserFactory {
                    if (!this::instance.isInitialized) {
                        this.instance = UserFactory()
                    }
                    return this.instance
                }
        }

    // Methods
        fun createUser(firstName: String, lastName: String, email: String, password: String, tU: TypeUser): User {
            // LocalDateTime.now() Timestamp
            return User(firstName, lastName, email, password, tU.toString(), LocalDateTime.now(), false, null, null)
        }
}
