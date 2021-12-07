package fr.enssat.kikeou.couturier_morizur.main.screens.listcontact

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import fr.enssat.kikeou.couturier_morizur.KikeouApplication
import fr.enssat.kikeou.couturier_morizur.KikeouViewModelFactory
import fr.enssat.kikeou.couturier_morizur.R
import fr.enssat.kikeou.couturier_morizur.database.dao.ContactDAO
import fr.enssat.kikeou.couturier_morizur.databinding.FragmentListContactBinding
import fr.enssat.kikeou.couturier_morizur.main.ContactDetailsActivity
import fr.enssat.kikeou.couturier_morizur.qrcode.QrCodeActivity
import fr.enssat.kikeou.couturier_morizur.qrcode.ReadQrCodeContract

class ListContactFragment : Fragment(), ListContactAdapter.CellClickListener {

    private var _binding: FragmentListContactBinding? = null
    private val binding get() = _binding!!

    private val listContactViewModel: ListContactViewModel by viewModels {
        var app = (activity?.application as KikeouApplication)
        KikeouViewModelFactory(app, null)
    }

    private val startForResult = registerForActivityResult(ReadQrCodeContract()) {
        try {
            val moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<ContactDAO.ContactAndLocation> =
                moshi.adapter(ContactDAO.ContactAndLocation::class.java)
            val contactAndLocation = jsonAdapter.fromJson(it)

            if (contactAndLocation != null) {
                listContactViewModel.addContactAndLocation(contactAndLocation)
                Toast.makeText(
                    context,
                    "Contact ${contactAndLocation?.contact?.firstname} added",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                throw Exception("No contact to add in room database")
            }
        } catch (e: Exception) {
            Log.e("kikeou-error", e.stackTraceToString());
            Toast.makeText(context, "This QR Code is not valid", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create adapter and link to the view
        val adapter = ListContactAdapter(this)
        binding.contactList.adapter = adapter

        listContactViewModel.listContact.observe(viewLifecycleOwner, {
            Log.e("aloha", "Location: ${it}")
            adapter.data = it
        })

        // Read QR Code button
        binding.scanQrButton.setOnClickListener{
            startForResult.launch(null)
        }
    }

    override fun onCellClickListener(data: ContactDAO.ContactListInfo) {
        val intent = Intent(context, ContactDetailsActivity::class.java)
        intent.putExtra("userId", data.id)
        startActivity(intent)
    }
}
