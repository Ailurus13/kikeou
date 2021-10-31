package fr.enssat.kikeou.couturier_morizur.main.screens.maincontact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import fr.enssat.kikeou.couturier_morizur.*
import fr.enssat.kikeou.couturier_morizur.databinding.FragmentMainContactBinding
import fr.enssat.kikeou.couturier_morizur.welcome.WelcomeActivityContract

class MainContactFragment : Fragment() {

    private var _binding: FragmentMainContactBinding? = null
    private val binding get() = _binding!!

    private val mainContactViewModel: MainContactViewModel by viewModels {
        KikeouViewModelFactory((activity?.application as KikeouApplication).contactRepository)
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

        mainContactViewModel.mainContact.observe(viewLifecycleOwner, {
            it?.let {
                binding.mainContactName.text = "Bonjour " + it.firstname + " ! "
            } ?: run {
                mainContactViewModel.startWelcomeActivity()
            }
        })

        mainContactViewModel.welcomeActivity.observe(viewLifecycleOwner, {
            if(it) {
                startForResult.launch(null)
            }
        })
    }
}
