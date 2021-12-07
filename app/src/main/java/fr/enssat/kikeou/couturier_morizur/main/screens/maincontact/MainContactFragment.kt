package fr.enssat.kikeou.couturier_morizur.main.screens.maincontact

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
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
import fr.enssat.kikeou.couturier_morizur.qrcode.QrCodeActivity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import fr.enssat.kikeou.couturier_morizur.database.dao.ContactDAO


class MainContactFragment : Fragment() {

    private var _binding: FragmentMainContactBinding? = null
    private val binding get() = _binding!!

    private val mainContactViewModel: MainContactViewModel by viewModels {
        KikeouViewModelFactory(activity?.application as KikeouApplication, null)
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
        val locationAdapter = LocationAdapter(mainContactViewModel, context)
        val locationRecyclerView = binding.locationRecyclerView
        locationRecyclerView.adapter = locationAdapter

        // Open add location modal on button click
        binding.buttonAddLocation.setOnClickListener {
            AddLocationFragmentDialog().show(
                childFragmentManager, AddLocationFragmentDialog.TAG
            )
        }

        // On contact details update
        mainContactViewModel.mainContact.observe(viewLifecycleOwner, {
            it?.let {
                binding.firstnameEditText.setText(it.contact.firstname)
                binding.lastnameEditText.setText(it.contact.lastname)
                locationAdapter.data = it.locations
            } ?: run {
                mainContactViewModel.startWelcomeActivity()
            }
        })

        // On firstname change
        binding.firstnameEditText.addTextChangedListener {
            if(it.toString() != mainContactViewModel.mainContact.value?.contact?.firstname) {
                binding.updateButtons.visibility = View.VISIBLE
            } else {
                binding.updateButtons.visibility = View.INVISIBLE
            }
        }

        // On lastname change
        binding.lastnameEditText.addTextChangedListener {
            if(it.toString() != mainContactViewModel.mainContact.value?.contact?.lastname) {
                binding.updateButtons.visibility = View.VISIBLE
            } else {
                binding.updateButtons.visibility = View.INVISIBLE
            }
        }

        // Cancel update button
        binding.cancelUpdate.setOnClickListener {
            binding.firstnameEditText.setText(mainContactViewModel.mainContact.value?.contact?.firstname)
            binding.lastnameEditText.setText(mainContactViewModel.mainContact.value?.contact?.lastname)
        }

        // Save update button
        binding.saveUpdate.setOnClickListener {
            mainContactViewModel.updateMainContact(binding.firstnameEditText.text.toString(), binding.lastnameEditText.text.toString())
        }

        // Qr Code Button
        binding.qrCodeButton.setOnClickListener{
            val intent = Intent(context, QrCodeActivity::class.java)
            val mainContact = mainContactViewModel.mainContact.value

            if(mainContact != null) {
                // Create JSON
                val moshi = Moshi.Builder().build()
                val jsonAdapter: JsonAdapter<ContactDAO.ContactAndLocation> = moshi.adapter(ContactDAO.ContactAndLocation::class.java)
                val json = jsonAdapter.toJson(mainContact)

                // Set intent extra
                intent.putExtra("data", json)
                intent.putExtra("title", "${mainContact.contact.firstname} ${mainContact.contact.lastname}")

                startActivity(intent)
            }
        }

        // Start welcome activity if there is no main contact yet
        mainContactViewModel.welcomeActivity.observe(viewLifecycleOwner, {
            if(it) {
                startForResult.launch(null)
            }
        })
    }
}
