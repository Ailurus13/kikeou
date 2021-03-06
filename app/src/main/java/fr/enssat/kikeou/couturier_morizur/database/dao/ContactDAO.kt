package fr.enssat.kikeou.couturier_morizur.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.squareup.moshi.JsonClass
import fr.enssat.kikeou.couturier_morizur.database.entity.Contact
import fr.enssat.kikeou.couturier_morizur.database.entity.Location

@Dao
interface ContactDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Query("SELECT * FROM contact WHERE id =:id")
    fun getById(id:String): LiveData<ContactAndLocation>

    @Query("SELECT * FROM contact WHERE isMainContact = 1")
    fun getMainContactAndLocation(): LiveData<ContactAndLocation>

    @Query("SELECT * FROM contact WHERE isMainContact = 1")
    fun getMainContact(): LiveData<Contact>

    @Query("SELECT contact.id, contact.firstname, contact.lastname, (SELECT location.value FROM location WHERE location.contactId =  contact.id AND location.week = :week AND location.day = :day) as locationValue  FROM contact WHERE isMainContact = 0")
    fun getAllContactListInfo(week: Int, day: Int): LiveData<List<ContactListInfo>>

    @Query("DELETE FROM contact WHERE contact.id = :id")
    suspend fun delete(id: String)

    data class ContactListInfo(
        val id: String,
        val firstname: String,
        val lastname: String,
        val locationValue: String?)

    @JsonClass(generateAdapter = true)
    data class ContactAndLocation(
        @Embedded
        var contact: Contact,

        @Relation(parentColumn =  "id", entityColumn = "contactId")
        val locations: List<Location>
    )
}
