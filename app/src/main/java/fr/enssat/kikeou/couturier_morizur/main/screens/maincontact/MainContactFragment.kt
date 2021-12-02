package fr.enssat.kikeou.couturier_morizur.main.screens.maincontact

import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import fr.enssat.kikeou.couturier_morizur.*
import fr.enssat.kikeou.couturier_morizur.databinding.FragmentMainContactBinding
import fr.enssat.kikeou.couturier_morizur.main.screens.addlocation.AddLocationFragmentDialog
import fr.enssat.kikeou.couturier_morizur.welcome.WelcomeActivityContract
import android.provider.Settings.Secure




class MainContactFragment : Fragment() {

    private var _binding: FragmentMainContactBinding? = null
    private val binding get() = _binding!!

    private val mainContactViewModel: MainContactViewModel by viewModels {
        KikeouViewModelFactory(activity?.application as KikeouApplication)
    }

    private val startForResult = registerForActivityResult(WelcomeActivityContract()) {
        mainContactViewModel.resetStartWelcomeActivity()
        it?.let {
            val androidId = Settings.Secure.getString(context?.contentResolver, Settings.Secure.ANDROID_ID)
            mainContactViewModel.createMainContact(androidId, it.firstname, it.lastname)
        } ?: run {
            mainContactViewModel.startWelcomeActivity()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        // On firstname change
        binding.firstnameEditText.addTextChangedListener {
            if(it.toString() != mainContactViewModel.mainContact.value?.firstname) {
                binding.updateButtons.visibility = View.VISIBLE
            } else {
                binding.updateButtons.visibility = View.INVISIBLE
            }
        }

        // On lastname change
        binding.lastnameEditText.addTextChangedListener {
            if(it.toString() != mainContactViewModel.mainContact.value?.lastname) {
                binding.updateButtons.visibility = View.VISIBLE
            } else {
                binding.updateButtons.visibility = View.INVISIBLE
            }
        }

        // Cancel update button
        binding.cancelUpdate.setOnClickListener {
            binding.firstnameEditText.setText(mainContactViewModel.mainContact.value?.firstname)
            binding.lastnameEditText.setText(mainContactViewModel.mainContact.value?.lastname)
        }

        // Save update button
        binding.saveUpdate.setOnClickListener {
            Log.e("aloha", "Updating...")
            mainContactViewModel.updateMainContact(binding.firstnameEditText.text.toString(), binding.lastnameEditText.text.toString())
        }

        // Start welcome activity if there is no main contact yet
        mainContactViewModel.welcomeActivity.observe(viewLifecycleOwner, {
            if(it) {
                startForResult.launch(null)
            }
        })
    }
}
