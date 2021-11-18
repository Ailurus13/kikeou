package fr.enssat.kikeou.couturier_morizur

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.enssat.kikeou.couturier_morizur.main.screens.addlocation.AddLocationViewModel
import fr.enssat.kikeou.couturier_morizur.main.screens.maincontact.MainContactViewModel

class KikeouViewModelFactory(private val app: KikeouApplication): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainContactViewModel::class.java)) {
            return MainContactViewModel(app.contactRepository) as T
        }
        if(modelClass.isAssignableFrom(AddLocationViewModel::class.java)) {
            return AddLocationViewModel(app.locationRepository, app.contactRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
