package fr.enssat.kikeou.couturier_morizur.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.enssat.kikeou.couturier_morizur.database.entity.Contact
import fr.enssat.kikeou.couturier_morizur.database.entity.Location

@Dao
interface LocationDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(location: Location): Long

    @Query("SELECT * FROM location JOIN contact ON location.contactId = contact.id WHERE contact.isMainContact = 1")
    fun getMainLocations(): LiveData<List<Location>>
}
