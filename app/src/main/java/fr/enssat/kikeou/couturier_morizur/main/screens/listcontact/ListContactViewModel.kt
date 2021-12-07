package fr.enssat.kikeou.couturier_morizur.main.screens.listcontact

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.enssat.kikeou.couturier_morizur.database.dao.ContactDAO
import fr.enssat.kikeou.couturier_morizur.database.repository.ContactRepository
import fr.enssat.kikeou.couturier_morizur.database.repository.LocationRepository
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.*

class ListContactViewModel(var contactRepository: ContactRepository, var locationRepository: LocationRepository): ViewModel() {
    var date = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    val listContact = contactRepository.getAllContactListInfo(week = 1, day = "Lundi")
    init {
        Log.e("aloha", "Date: $date")
    }


    fun addContactAndLocation(contactAndLocation: ContactDAO.ContactAndLocation) {
        viewModelScope.launch {
            contactRepository.createContact(contactAndLocation.contact)

            // TODO: Create if not exists in room
            // TODO: Delte in room if not exists in JSON
            contactAndLocation.locations.forEach{
                locationRepository.createLocation(it)
            }
        }
    }
}
