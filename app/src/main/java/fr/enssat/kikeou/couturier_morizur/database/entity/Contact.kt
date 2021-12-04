package fr.enssat.kikeou.couturier_morizur.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey
    var id: String,
    var firstname: String,
    var lastname: String,
    var email: String?,
    var tel: String?,
    var isMainContact: Boolean
    ) {

    constructor(id: String, firstname: String, lastname: String): this(id, firstname, lastname, null, null, false)
}
