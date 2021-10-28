package fr.enssat.kikeou.couturier_morizur.database.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import fr.enssat.kikeou.couturier_morizur.database.dao.ContactDAO
import fr.enssat.kikeou.couturier_morizur.database.entity.Contact

class ContactRepository(val contactDao: ContactDAO) {

    @WorkerThread
    suspend fun getById(id: String): LiveData<Contact> {
        return contactDao.get(id)
    }

    @WorkerThread
    suspend fun save(contact: Contact) {
        contactDao.save(contact)
    }
}
