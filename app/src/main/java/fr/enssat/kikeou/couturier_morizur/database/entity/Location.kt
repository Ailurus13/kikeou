package fr.enssat.kikeou.couturier_morizur.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "location", foreignKeys = [
    ForeignKey(entity = Contact::class, parentColumns = ["id"], childColumns = ["contactId"], onDelete = ForeignKey.CASCADE)
], indices = [
    Index("contactId")
])
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var day: String,
    var week: Int,
    var value: String,
    var contactId: String
) {
    constructor(day: String, week: Int, value:String, contactId:String): this(0, day, week, value, contactId)
}
