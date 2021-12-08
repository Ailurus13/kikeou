package fr.enssat.kikeou.couturier_morizur.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.enssat.kikeou.couturier_morizur.database.repository.ContactRepository
import java.util.*

class ContactDetailsViewModel(
    userId: String,
    contactRepository: ContactRepository): ViewModel() {
        private var calendar: Calendar = Calendar.getInstance(Locale.FRANCE)
        private val weekNumber = calendar.get(Calendar.WEEK_OF_YEAR)
        val selectedWeek = MutableLiveData<Int>(weekNumber)
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
