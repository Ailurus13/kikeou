package fr.enssat.kikeou.couturier_morizur

import androidx.lifecycle.ViewModel
import fr.enssat.kikeou.couturier_morizur.database.entity.Contact
import fr.enssat.kikeou.couturier_morizur.database.repository.ContactRepository

class KikeouViewModel(private val contactRepository: ContactRepository): ViewModel() {

    // The only contact created in database at start
    val contact = contactRepository.getById("1")

    // Get informations to display in the recycler view
    val contactsListInfo = contactRepository.getAllContactListInfo()
}
