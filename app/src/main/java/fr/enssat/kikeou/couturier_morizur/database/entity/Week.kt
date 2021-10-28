package fr.enssat.kikeou.couturier_morizur.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "week", foreignKeys = [
    ForeignKey(entity = Contact::class, parentColumns = ["id"], childColumns = ["locationId"], onDelete = ForeignKey.CASCADE)
])
data class Week(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var week: Int,
    var contactId: String,
)
