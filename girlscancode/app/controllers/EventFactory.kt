package girlscancode.app.controllers

import girlscancode.app.enums.TypeUser
import girlscancode.app.models.Event
import java.time.LocalDateTime

class EventFactory {
    // Attributes
    companion object {
        // Attributes
            private lateinit var instance: EventFactory
        // Methods
            fun getInstance(): EventFactory {
                if (!this::instance.isInitialized) {
                    this.instance = EventFactory()
                }
                return this.instance
            }
    }

    // Methods
        fun createEvent(name: String, description: String, startDate: String, endDate: String, address: String, link: String): Event {
            // LocalDateTime.now() Timestamp
            return Event(name, description, startDate, endDate, address, link, LocalDateTime.now(), null)
        }
}