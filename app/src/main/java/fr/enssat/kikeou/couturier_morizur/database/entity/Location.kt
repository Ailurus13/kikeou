package fr.enssat.kikeou.couturier_morizur.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "location", foreignKeys = [
    ForeignKey(entity = Week::class, parentColumns = ["id"], childColumns = ["weekdId"], onDelete = ForeignKey.CASCADE)
])
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var day: Int,
    var value: String,
    var weekdId: Long
)
