package fr.enssat.kikeou.couturier_morizur.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.enssat.kikeou.couturier_morizur.database.entity.Contact

@Dao
interface ContactDAO {
    @Query("SELECT * FROM contact WHERE id =:id")
    fun get(id:String): LiveData<Contact>

    @Query("SELECT contact.firstname, contact.lastname FROM contact")
    fun getAllContactListInfo(): LiveData<List<ContactListInfo>>

    @Insert
    suspend fun save(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    data class ContactListInfo(val firstname: String, val lastname: String)
}
