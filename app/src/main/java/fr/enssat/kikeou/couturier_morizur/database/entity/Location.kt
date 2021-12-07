package fr.enssat.kikeou.couturier_morizur.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "location", primaryKeys = ["day", "week", "contactId"], foreignKeys = [
    ForeignKey(entity = Contact::class, parentColumns = ["id"], childColumns = ["contactId"], onDelete = ForeignKey.CASCADE)
], indices = [
    Index("contactId")
])
data class Location(
    var day: Int,
    var week: Int,
    var value: String,
    var contactId: String
)

