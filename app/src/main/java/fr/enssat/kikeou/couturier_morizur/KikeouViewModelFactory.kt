package fr.enssat.kikeou.couturier_morizur

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.enssat.kikeou.couturier_morizur.database.repository.ContactRepository
import fr.enssat.kikeou.couturier_morizur.main.screens.listcontact.ListContactViewModel
import fr.enssat.kikeou.couturier_morizur.main.screens.maincontact.MainContactViewModel

class KikeouViewModelFactory(private val contactRepository: ContactRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainContactViewModel::class.java)) {
            return MainContactViewModel(contactRepository) as T
        }
        if(modelClass.isAssignableFrom(ListContactViewModel::class.java)) {
            return ListContactViewModel(contactRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
