package fr.enssat.kikeou.couturier_morizur.main.screens.listcontact

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.zxing.qrcode.QRCodeReader
import fr.enssat.kikeou.couturier_morizur.R
import fr.enssat.kikeou.couturier_morizur.databinding.FragmentListContactBinding
import fr.enssat.kikeou.couturier_morizur.databinding.FragmentMainContactBinding
import fr.enssat.kikeou.couturier_morizur.qrcode.QrCodeActivity
import fr.enssat.kikeou.couturier_morizur.qrcode.ReadQrCodeActivity
import fr.enssat.kikeou.couturier_morizur.qrcode.ReadQrCodeContract
import fr.enssat.kikeou.couturier_morizur.welcome.WelcomeActivityContract

class ListContactFragment : Fragment() {

    private var _binding: FragmentListContactBinding? = null
    private val binding get() = _binding!!

    private val startForResult = registerForActivityResult(ReadQrCodeContract()) {
        // TODO: Parse json to Contact and Locations
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
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
