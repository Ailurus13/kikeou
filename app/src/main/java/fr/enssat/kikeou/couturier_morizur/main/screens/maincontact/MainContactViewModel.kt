package fr.enssat.kikeou.couturier_morizur.main.screens.maincontact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.enssat.kikeou.couturier_morizur.database.repository.ContactRepository
import kotlinx.coroutines.launch

class MainContactViewModel(private val contactRepository: ContactRepository): ViewModel() {
    val mainContact = contactRepository.getMainContact()

    val welcomeActivity: LiveData<Boolean>
        get() = _welcomeActivity
    private val _welcomeActivity = MutableLiveData<Boolean>(false)

    fun createMainContact(firstname:String, lastname:String) {
        viewModelScope.launch {
            contactRepository.createMainContact(firstname, lastname)
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
