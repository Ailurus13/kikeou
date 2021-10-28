package fr.enssat.kikeou.couturier_morizur.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey
    var id: String,
    var firstname: String,
    var lastname: String,
    var email: String,
    var tel: String,
)
