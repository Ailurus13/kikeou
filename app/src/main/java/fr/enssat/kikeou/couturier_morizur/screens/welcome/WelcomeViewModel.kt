package fr.enssat.kikeou.couturier_morizur.screens.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.enssat.kikeou.couturier_morizur.database.repository.ContactRepository
import kotlinx.coroutines.launch

class WelcomeViewModel(private val contactRepository: ContactRepository): ViewModel() {

    val navigateToNextFragment : LiveData<Boolean>
        get() = _navigateToNextFragment
    private val _navigateToNextFragment = MutableLiveData<Boolean>()

    // Reset navigation
    fun doneNavigate() {
        _navigateToNextFragment.value = false
    }

    fun onNext(firstname:String, lastname: String) {
        viewModelScope.launch {
            // Create contact
            contactRepository.createMainContact(firstname, lastname)
            // Trigger navigation
            _navigateToNextFragment.value = true
        }
    }
}
