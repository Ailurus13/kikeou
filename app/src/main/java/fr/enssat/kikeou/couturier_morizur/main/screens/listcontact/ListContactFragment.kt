package fr.enssat.kikeou.couturier_morizur.main.screens.listcontact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import fr.enssat.kikeou.couturier_morizur.database.dao.ContactDAO
import fr.enssat.kikeou.couturier_morizur.databinding.FragmentListContactBinding
import fr.enssat.kikeou.couturier_morizur.qrcode.ReadQrCodeContract

class ListContactFragment : Fragment() {

    private var _binding: FragmentListContactBinding? = null
    private val binding get() = _binding!!

    private val startForResult = registerForActivityResult(ReadQrCodeContract()) {
        try {
            val moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<ContactDAO.ContactAndLocation> = moshi.adapter(ContactDAO.ContactAndLocation::class.java)
            val contactAndLocation = jsonAdapter.fromJson(it)

            Toast.makeText(context, "Contact ${contactAndLocation?.contact?.firstname} added", Toast.LENGTH_LONG).show()
        } catch(e: Exception) {
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

        binding.scanQrButton.setOnClickListener{
            startForResult.launch(null)
        }
    }
}
