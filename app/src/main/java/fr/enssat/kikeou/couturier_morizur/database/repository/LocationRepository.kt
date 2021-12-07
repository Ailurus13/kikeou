package fr.enssat.kikeou.couturier_morizur.database.repository

import fr.enssat.kikeou.couturier_morizur.database.dao.LocationDAO
import fr.enssat.kikeou.couturier_morizur.database.entity.Location

class LocationRepository(private val locationDAO: LocationDAO) {
    suspend fun getLocationsByContact(contactId: String): List<Location> {
        return locationDAO.getLocationsByContact(contactId)
    }

    suspend fun createLocation(day: String, week: Int, value:String, contactId: String) {
        var location = Location(day, week, value, contactId)
        locationDAO.create(location)
    }

    suspend fun createLocation(location: Location) {
        locationDAO.create(location)
    }

    suspend fun deleteLocation(location: Location) {
        locationDAO.delete(location)
    }
}
