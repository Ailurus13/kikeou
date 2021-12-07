package fr.enssat.kikeou.couturier_morizur.main.screens.addlocation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.enssat.kikeou.couturier_morizur.database.repository.ContactRepository
import fr.enssat.kikeou.couturier_morizur.database.repository.LocationRepository
import kotlinx.coroutines.launch

class AddLocationViewModel(private val locationRepository: LocationRepository, private val contactRepository: ContactRepository): ViewModel() {
    var mainContact = contactRepository.getMainContact();

    fun addLocation(day: Int, week: Int, value: String) {
        viewModelScope.launch {
            mainContact.value?.let {
                locationRepository.createLocation(day, week, value, it.id)
            }
        }
    }
}
