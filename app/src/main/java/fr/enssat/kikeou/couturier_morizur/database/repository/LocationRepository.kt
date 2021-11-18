package fr.enssat.kikeou.couturier_morizur.database.repository

import fr.enssat.kikeou.couturier_morizur.database.dao.LocationDAO
import fr.enssat.kikeou.couturier_morizur.database.entity.Location

class LocationRepository(private val locationDAO: LocationDAO) {
    suspend fun createLocation(week: Int, day: Int, value:String, contactId: Long) {
        var location = Location(week, day, value, contactId)
        locationDAO.create(location)
    }
}
