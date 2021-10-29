package fr.enssat.kikeou.couturier_morizur.screens.welcome

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import fr.enssat.kikeou.couturier_morizur.KikeouApplication
import fr.enssat.kikeou.couturier_morizur.KikeouViewModelFactory
import fr.enssat.kikeou.couturier_morizur.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    private val welcomeViewModel: WelcomeViewModel by viewModels {
        KikeouViewModelFactory((activity?.application as KikeouApplication).contactRepository)
    }

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
            welcomeViewModel.onNext(
                binding.firstnameEditText.text.toString(),
                binding.lastnameEditText.text.toString()
            )
        }

        welcomeViewModel.navigateToNextFragment.observe(viewLifecycleOwner, {
            if(it) {
                val action = WelcomeFragmentDirections.actionWelcomeFragmentToMainContactFragment()
                NavHostFragment.findNavController(this).navigate(action)
                welcomeViewModel.doneNavigate()
            }
        })
    }
}
