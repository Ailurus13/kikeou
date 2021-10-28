package fr.enssat.kikeou.couturier_morizur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val kikeouApp = application as KikeouApplication

        // TODO: Regarder fonctionnement coroutine + vérifier bon fonctionnement db
        kikeouApp.contactRepository.getById("1")
    }
}
