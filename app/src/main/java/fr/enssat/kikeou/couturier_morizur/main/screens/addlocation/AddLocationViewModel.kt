package fr.enssat.kikeou.couturier_morizur.main.screens.addlocation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.enssat.kikeou.couturier_morizur.database.repository.ContactRepository
import fr.enssat.kikeou.couturier_morizur.database.repository.LocationRepository
import kotlinx.coroutines.launch

class AddLocationViewModel(private val locationRepository: LocationRepository, private val contactRepository: ContactRepository): ViewModel() {
    var mainContact = contactRepository.getMainContact();

    fun addLocation(week: Int, day: Int, value: String) {
        viewModelScope.launch {

            mainContact.value?.let {
                locationRepository.createLocation(week, day, value, it.id)
            }
        }
    }
}
