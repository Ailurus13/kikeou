package fr.enssat.kikeou.couturier_morizur.welcome.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.enssat.kikeou.couturier_morizur.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {
            var data = Intent()
            data.putExtra("firstname", binding.firstnameEditText.text.toString())
            data.putExtra("lastname", binding.lastnameEditText.text.toString())
            activity?.setResult(Activity.RESULT_OK, data)
            activity?.finish()
        }
    }
}
