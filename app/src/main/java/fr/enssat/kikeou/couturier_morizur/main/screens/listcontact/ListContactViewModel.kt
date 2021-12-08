package fr.enssat.kikeou.couturier_morizur.main.screens.listcontact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.enssat.kikeou.couturier_morizur.database.dao.ContactDAO
import fr.enssat.kikeou.couturier_morizur.database.repository.ContactRepository
import fr.enssat.kikeou.couturier_morizur.database.repository.LocationRepository
import kotlinx.coroutines.launch
import java.util.*

class ListContactViewModel(var contactRepository: ContactRepository, var locationRepository: LocationRepository): ViewModel() {
    private var calendar: Calendar = Calendar.getInstance(Locale.FRANCE)
    private val weekNumber = calendar.get(Calendar.WEEK_OF_YEAR)
    private val dayNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1
    val listContact = contactRepository.getAllContactListInfo(weekNumber, dayNumber)

    fun deleteContact(id: String) {
        viewModelScope.launch {
            contactRepository.deleteContact(id)
        }
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
