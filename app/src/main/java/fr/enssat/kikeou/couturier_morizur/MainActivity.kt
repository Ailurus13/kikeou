package fr.enssat.kikeou.couturier_morizur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private val wordViewModel: KikeouViewModel by viewModels {
        KikeouViewModelFactory((application as KikeouApplication).contactRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordViewModel.contact.observe(this, {
            Log.e("Aloha Testing", it.firstname + " " + it.lastname)
        })

        wordViewModel.contactsListInfo.observe(this, {
            Log.e("Aloha Testing", it.size.toString())
        })
    }
}
