package fr.enssat.kikeou.couturier_morizur.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import fr.enssat.kikeou.couturier_morizur.KikeouApplication
import fr.enssat.kikeou.couturier_morizur.KikeouViewModelFactory
import fr.enssat.kikeou.couturier_morizur.R
import fr.enssat.kikeou.couturier_morizur.database.entity.Location
import fr.enssat.kikeou.couturier_morizur.databinding.ActivityContactDetailsBinding
import fr.enssat.kikeou.couturier_morizur.main.screens.maincontact.ReadOnlyLocationAdapter

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

    private fun updateLocationData(locationAdapter: ReadOnlyLocationAdapter, locations: List<Location>) {
        // Filter by week
        // /!\ It would be better to get from database only the needed data
        // but we could not make it work /!\
        locationAdapter.data = locations.filter { location ->
            location.week == contactDetailsViewModel.selectedWeek.value
        }
    }

    override fun onResume() {
        super.onResume()

        val adapter = ReadOnlyLocationAdapter(this)
        binding.detailsLocationRecyclerView.adapter = adapter

        binding.nextWeek.setOnClickListener {
            contactDetailsViewModel.nextWeek()
        }

        binding.previousWeek.setOnClickListener {
            contactDetailsViewModel.previousWeek()
        }

        contactDetailsViewModel.selectedWeek.observe(this, {
            binding.weekText.text = getString(R.string.week_number, it)
            contactDetailsViewModel.contact.value?.let { contact -> updateLocationData(adapter, contact.locations) }
        })

        contactDetailsViewModel.contact.observe(this, {
            binding.contactDetailsFirstname.text = it.contact.firstname
            binding.contactDetailsLastname.text = it.contact.lastname
            updateLocationData(adapter, it.locations)
        })
    }
}
