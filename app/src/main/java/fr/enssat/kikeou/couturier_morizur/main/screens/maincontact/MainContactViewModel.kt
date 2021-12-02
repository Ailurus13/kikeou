package fr.enssat.kikeou.couturier_morizur.main.screens.maincontact

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.enssat.kikeou.couturier_morizur.database.repository.ContactRepository
import fr.enssat.kikeou.couturier_morizur.database.repository.LocationRepository
import kotlinx.coroutines.launch

class MainContactViewModel(private val contactRepository: ContactRepository, private val locationRepository: LocationRepository): ViewModel() {
    val mainContact = contactRepository.getMainContact()
    val locations = locationRepository.getMainLocations()

    val welcomeActivity: LiveData<Boolean>
        get() = _welcomeActivity
    private val _welcomeActivity = MutableLiveData<Boolean>(false)

    fun createMainContact(id: String, firstname:String, lastname:String) {
        viewModelScope.launch {
            contactRepository.createMainContact(id, firstname, lastname)
        }
    }

    fun updateMainContact(firstname: String, lastname: String) {
        viewModelScope.launch {
            var mainContactValue = mainContact.value
            if(mainContactValue != null) {
                mainContactValue.lastname = lastname
                mainContactValue.firstname = firstname
                contactRepository.update(mainContactValue)
            }
        }
    }

    fun startWelcomeActivity() {
        if(_welcomeActivity.value == false) {
            _welcomeActivity.value = true
        }
    }

    fun resetStartWelcomeActivity() {
        if(_welcomeActivity.value == true) {
            _welcomeActivity.value = false
        }
    }
}
