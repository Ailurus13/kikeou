package fr.enssat.kikeou.couturier_morizur.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.enssat.kikeou.couturier_morizur.database.repository.ContactRepository
import fr.enssat.kikeou.couturier_morizur.database.repository.LocationRepository

class ContactDetailsViewModel(
    userId: String,
    contactRepository: ContactRepository,
    private val locationRepository: LocationRepository): ViewModel() {
        val selectedWeek = MutableLiveData<Int>(1)
        var contact = contactRepository.getById(userId)

    fun nextWeek() {
        if(selectedWeek.value == 53){
            selectedWeek.value = 1
        } else {
            selectedWeek.value = selectedWeek.value?.plus(1)
        }
    }

    fun previousWeek() {
        if(selectedWeek.value == 1){
            selectedWeek.value = 53
        } else {
            selectedWeek.value = selectedWeek.value?.minus(1)
        }
    }
    }
