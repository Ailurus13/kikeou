package fr.enssat.kikeou.couturier_morizur

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.enssat.kikeou.couturier_morizur.main.ContactDetailsViewModel
import fr.enssat.kikeou.couturier_morizur.main.screens.addlocation.AddLocationViewModel
import fr.enssat.kikeou.couturier_morizur.main.screens.listcontact.ListContactViewModel
import fr.enssat.kikeou.couturier_morizur.main.screens.addlocation.AddLocationViewModel
import fr.enssat.kikeou.couturier_morizur.main.screens.maincontact.MainContactViewModel

class KikeouViewModelFactory(
    private val app: KikeouApplication,
    private val arg: String?): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainContactViewModel::class.java)) {
            return MainContactViewModel(app.contactRepository, app.locationRepository) as T
        }
        if(modelClass.isAssignableFrom(AddLocationViewModel::class.java)) {
            return AddLocationViewModel(app.locationRepository, app.contactRepository) as T
        }
        if(modelClass.isAssignableFrom(ListContactViewModel::class.java)) {
            return ListContactViewModel(app.contactRepository, app.locationRepository) as T
        }
        if(modelClass.isAssignableFrom(ContactDetailsViewModel::class.java)) {
            return arg?.let { ContactDetailsViewModel(it, app.contactRepository, app.locationRepository) } as T
        }
        throw IllegalArgumentException("Unknown ViewModel class or arg error")
    }
}
