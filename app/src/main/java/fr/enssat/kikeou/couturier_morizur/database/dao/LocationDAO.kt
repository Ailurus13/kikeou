package fr.enssat.kikeou.couturier_morizur.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.lifecycle.LiveData
import fr.enssat.kikeou.couturier_morizur.database.entity.Contact
import fr.enssat.kikeou.couturier_morizur.database.entity.Location

@Dao
interface LocationDAO {
    @Insert
    suspend fun create(location: Location): Long

    @Query("SELECT * FROM location JOIN contact ON location.contactId = contact.id WHERE contact.isMainContact = 1")
    fun getMainLocations(): LiveData<List<Location>>
}
