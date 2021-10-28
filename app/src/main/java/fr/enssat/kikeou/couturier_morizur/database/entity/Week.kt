package fr.enssat.kikeou.couturier_morizur.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "week", foreignKeys = [
    ForeignKey(entity = Contact::class, parentColumns = ["id"], childColumns = ["contactId"], onDelete = ForeignKey.CASCADE)
], indices = [
    Index("contactId")
])
data class Week(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var week: Int,
    var contactId: String,
)
