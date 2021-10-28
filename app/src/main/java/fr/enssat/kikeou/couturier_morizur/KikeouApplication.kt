package fr.enssat.kikeou.couturier_morizur

import android.app.Application
import fr.enssat.kikeou.couturier_morizur.database.KikeouDatabase
import fr.enssat.kikeou.couturier_morizur.database.repository.ContactRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class KikeouApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { KikeouDatabase.getInstance(this, applicationScope) }
    val contactRepository by lazy { ContactRepository(database.contactDAO) }
}
