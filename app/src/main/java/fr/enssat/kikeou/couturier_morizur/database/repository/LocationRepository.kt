package fr.enssat.kikeou.couturier_morizur.database.repository

import androidx.lifecycle.LiveData
import fr.enssat.kikeou.couturier_morizur.database.dao.LocationDAO
import fr.enssat.kikeou.couturier_morizur.database.entity.Location

class LocationRepository(private val locationDAO: LocationDAO) {
    suspend fun createLocation(day: String, week: Int, value:String, contactId: String) {
        var location = Location(day, week, value, contactId)
        locationDAO.create(location)
    }

    fun getMainLocations(): LiveData<List<Location>> {
        return locationDAO.getMainLocations()
    }
}
