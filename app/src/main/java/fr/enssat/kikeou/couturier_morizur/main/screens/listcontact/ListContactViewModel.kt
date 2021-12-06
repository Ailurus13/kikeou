package fr.enssat.kikeou.couturier_morizur.main.screens.listcontact

import android.util.Log
import androidx.lifecycle.ViewModel
import fr.enssat.kikeou.couturier_morizur.database.repository.ContactRepository

class ListContactViewModel(private val contactRepository: ContactRepository): ViewModel() {
    val listContact = contactRepository.getAllContactListInfo()
}
