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
    fun getById(id:String): LiveData<Contact>

    @Query("SELECT * FROM contact WHERE isMainContact = 1")
    fun getMainContactAndLocation(): LiveData<ContactAndLocation>

    @Query("SELECT * FROM contact WHERE isMainContact = 1")
    fun getMainContact(): LiveData<Contact>

    @Query("SELECT contact.firstname, contact.lastname FROM contact JOIN location ON contact.id = location.contactId WHERE location.week = :week AND location.day = :day")
    fun getAllContactListInfo(week: Int, day: String): LiveData<List<ContactListInfo>>

    data class ContactListInfo(val firstname: String, val lastname: String)

    @JsonClass(generateAdapter = true)
    data class ContactAndLocation(
        @Embedded
        var contact: Contact,

        @Relation(parentColumn =  "id", entityColumn = "contactId")
        val locations: List<Location>
    )
}
