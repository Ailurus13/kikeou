package fr.enssat.kikeou.couturier_morizur.database.repository

import androidx.lifecycle.LiveData
import fr.enssat.kikeou.couturier_morizur.database.dao.ContactDAO
import fr.enssat.kikeou.couturier_morizur.database.entity.Contact

class ContactRepository(private val contactDao: ContactDAO) {

    suspend fun createMainContact(firstname: String, lastname:String) {
        var mainContact = Contact(firstname, lastname)
        mainContact.isMainContact = true
        contactDao.create(mainContact)
    }

    fun getAllContactListInfo(): LiveData<List<ContactDAO.ContactListInfo>> {
        return contactDao.getAllContactListInfo()
    }

    fun getById(id: String): LiveData<Contact> {
        return contactDao.get(id)
    }
}
