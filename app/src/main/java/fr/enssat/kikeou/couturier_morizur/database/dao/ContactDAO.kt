package fr.enssat.kikeou.couturier_morizur.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.enssat.kikeou.couturier_morizur.database.entity.Contact

@Dao
interface ContactDAO {
    @Insert
    suspend fun save(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Query("SELECT * FROM contact WHERE id =:id")
    suspend fun get(id:String): LiveData<Contact>
}
