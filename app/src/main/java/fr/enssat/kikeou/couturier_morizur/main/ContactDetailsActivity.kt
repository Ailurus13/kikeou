package fr.enssat.kikeou.couturier_morizur.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import fr.enssat.kikeou.couturier_morizur.KikeouApplication
import fr.enssat.kikeou.couturier_morizur.KikeouViewModelFactory
import fr.enssat.kikeou.couturier_morizur.databinding.ActivityContactDetailsBinding

class ContactDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDetailsBinding

    private val contactDetailsViewModel: ContactDetailsViewModel by viewModels {
        KikeouViewModelFactory(application as KikeouApplication, intent.getStringExtra("userId"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        contactDetailsViewModel.contact.observe(this, {
            binding.contactDetailsFirstname.text = it.firstname
            binding.contactDetailsLastname.text = it.lastname
        })
    }
}
