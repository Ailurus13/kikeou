package fr.enssat.kikeou.couturier_morizur.database.repository

import androidx.lifecycle.LiveData
import fr.enssat.kikeou.couturier_morizur.database.dao.ContactDAO
import fr.enssat.kikeou.couturier_morizur.database.entity.Contact

class ContactRepository(private val contactDao: ContactDAO) {

    suspend fun createMainContact(id: String, firstname: String, lastname:String) {
        var mainContact = Contact(id, firstname, lastname)
        mainContact.isMainContact = true
        contactDao.create(mainContact)
    }

    suspend fun createContact(contact: Contact) {
        contact.isMainContact = false
        contactDao.create(contact)
    }

    suspend fun update(contact: Contact) {
        contactDao.update(contact)
    }

    fun getMainContactAndLocation(): LiveData<ContactDAO.ContactAndLocation> {
        return contactDao.getMainContactAndLocation()
    }

    fun getMainContact(): LiveData<Contact> {
        return contactDao.getMainContact()
    }
    fun getAllContactListInfo(): LiveData<List<ContactDAO.ContactListInfo>> {
        return contactDao.getAllContactListInfo()
    }

    fun getById(userId: String): LiveData<Contact> {
        return contactDao.getById(userId)
    }
}
