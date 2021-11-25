package fr.enssat.kikeou.couturier_morizur.main.screens.maincontact

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import fr.enssat.kikeou.couturier_morizur.*
import fr.enssat.kikeou.couturier_morizur.databinding.FragmentMainContactBinding
import fr.enssat.kikeou.couturier_morizur.main.screens.addlocation.AddLocationFragmentDialog
import fr.enssat.kikeou.couturier_morizur.welcome.WelcomeActivityContract

class MainContactFragment : Fragment() {

    private var _binding: FragmentMainContactBinding? = null
    private val binding get() = _binding!!

    private val mainContactViewModel: MainContactViewModel by viewModels {
        KikeouViewModelFactory(activity?.application as KikeouApplication)
    }

    private val startForResult = registerForActivityResult(WelcomeActivityContract()) {
        mainContactViewModel.resetStartWelcomeActivity()
        it?.let {
            mainContactViewModel.createMainContact(it.firstname, it.lastname)
        } ?: run {
            mainContactViewModel.startWelcomeActivity()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Location RecyclerView
        val locationAdapter = LocationAdapter()
        val locationRecyclerView = binding.locationRecyclerView
        locationRecyclerView.adapter = locationAdapter

        // On locations change
        mainContactViewModel.locations.observe(viewLifecycleOwner, {
            locationAdapter.data = it
        })

        // Open add location modal on button click
        binding.buttonAddLocation.setOnClickListener {
            AddLocationFragmentDialog().show(
                childFragmentManager, AddLocationFragmentDialog.TAG
            )
        }

        // On contact details update
        mainContactViewModel.mainContact.observe(viewLifecycleOwner, {
            it?.let {
                binding.firstnameEditText.setText(it.firstname)
                binding.lastnameEditText.setText(it.lastname)
            } ?: run {
                mainContactViewModel.startWelcomeActivity()
            }
        })

        // Start welcome activity if there is no main contact yet
        mainContactViewModel.welcomeActivity.observe(viewLifecycleOwner, {
            if(it) {
                startForResult.launch(null)
            }
        })
    }
}
