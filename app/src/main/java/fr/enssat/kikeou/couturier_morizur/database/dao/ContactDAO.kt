package fr.enssat.kikeou.couturier_morizur.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.lifecycle.LiveData
import fr.enssat.kikeou.couturier_morizur.database.entity.Contact

@Dao
interface ContactDAO {
    @Insert
    suspend fun create(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Query("SELECT * FROM contact WHERE id =:id")
    fun getById(id:String): LiveData<Contact>

    @Query("SELECT * FROM contact WHERE isMainContact = 1")
    fun getMainContact(): LiveData<Contact>

    @Query("SELECT contact.firstname, contact.lastname FROM contact")
    fun getAllContactListInfo(): LiveData<List<ContactListInfo>>

    data class ContactListInfo(val firstname: String, val lastname: String)
}
