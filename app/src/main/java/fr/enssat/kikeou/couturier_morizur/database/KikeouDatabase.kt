package fr.enssat.kikeou.couturier_morizur.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase

import fr.enssat.kikeou.couturier_morizur.database.dao.ContactDAO
import fr.enssat.kikeou.couturier_morizur.database.dao.LocationDAO
import fr.enssat.kikeou.couturier_morizur.database.entity.Contact
import fr.enssat.kikeou.couturier_morizur.database.entity.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Contact::class, Location::class], version=1, exportSchema = false)
abstract class KikeouDatabase: RoomDatabase() {

    abstract  val contactDAO: ContactDAO
    abstract  val locationDAO: LocationDAO

    companion object {
        private const val DATABASE_NAME = "kikeou_database"

        @Volatile
        private var INSTANCE: KikeouDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): KikeouDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, KikeouDatabase::class.java, DATABASE_NAME).addCallback(KikeouDatabaseCallback(scope)).build()
                    INSTANCE = instance
                }

                return instance;
            }
        }
    }

    private class KikeouDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.contactDAO)
                }
            }
        }

        fun populateDatabase(contactDao: ContactDAO) {
            // Function called to populate db during development
        }
    }
}
