package girlscancode.app.models

import java.time.LocalDateTime

data class Keyword(
    private var name: String,
    private var createdAt: LocalDateTime,
    private var updatedAt: LocalDateTime?
) {
    // Getters
        fun getName(): String {
            return this.name
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
        fun setCreatedAt(date: LocalDateTime) {
            this.createdAt = date
        }
        fun setUpdatedAt(date: LocalDateTime) {
            this.updatedAt = date
        }
}
