package fr.enssat.kikeou.couturier_morizur.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var firstname: String,
    var lastname: String,
    var email: String?,
    var tel: String?,
    var isMainContact: Boolean
    ) {
    constructor(firstname: String, lastname: String): this(0, firstname, lastname, null, null, false)
    constructor(firstname: String, lastname: String, email:String, tel:String): this(0, firstname, lastname, email, tel, false)
}
