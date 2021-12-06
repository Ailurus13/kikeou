package fr.enssat.kikeou.couturier_morizur.main.screens.listcontact

import androidx.lifecycle.ViewModel
import fr.enssat.kikeou.couturier_morizur.database.repository.ContactRepository

class ListContactViewModel(contactRepository: ContactRepository): ViewModel() {
    val listContact = contactRepository.getAllContactListInfo()
}
