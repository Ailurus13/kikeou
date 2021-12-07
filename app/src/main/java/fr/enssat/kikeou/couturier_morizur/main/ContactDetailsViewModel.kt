package fr.enssat.kikeou.couturier_morizur.main

import androidx.lifecycle.ViewModel
import fr.enssat.kikeou.couturier_morizur.database.repository.ContactRepository
import fr.enssat.kikeou.couturier_morizur.database.repository.LocationRepository

class ContactDetailsViewModel(
    userId: String,
    contactRepository: ContactRepository,
    private val locationRepository: LocationRepository): ViewModel() {
        var contact = contactRepository.getById(userId)
    }
