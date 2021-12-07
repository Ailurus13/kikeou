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
        // Sync locations with contactAndLocation object
        viewModelScope.launch {
            // Create contact if not exists
            contactRepository.createContact(contactAndLocation.contact)

            // Get actual locations of the contact if they exits
            val locations = locationRepository.getLocationsByContact(contactAndLocation.contact.id)

            // Delete all locations that are not in the contactAndLocation object
            locations.forEach{
                if(!contactAndLocation.locations.contains(it)) {
                    locationRepository.deleteLocation(it)
                }
            }

            // Create / Update all the remaining locations
            contactAndLocation.locations.forEach{
                locationRepository.createLocation(it)
            }
        }
    }
}
