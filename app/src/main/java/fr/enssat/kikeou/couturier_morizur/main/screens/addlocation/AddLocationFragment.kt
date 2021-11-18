package fr.enssat.kikeou.couturier_morizur.main.screens.addlocation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import fr.enssat.kikeou.couturier_morizur.KikeouApplication
import fr.enssat.kikeou.couturier_morizur.KikeouViewModelFactory
import fr.enssat.kikeou.couturier_morizur.R
import fr.enssat.kikeou.couturier_morizur.databinding.FragmentAddLocationBinding

class AddLocationFragment : Fragment() {
    private var _binding: FragmentAddLocationBinding? = null
    private val binding get() = _binding!!

    private val addLocationViewModel: AddLocationViewModel by viewModels {
        KikeouViewModelFactory((activity?.application as KikeouApplication))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddLocationBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.weekNumberPicker.minValue = 1;
        binding.weekNumberPicker.maxValue = 53;

        activity?.let {
            ArrayAdapter.createFromResource(it,
                R.array.days,
                R.layout.support_simple_spinner_dropdown_item
            ).also { adapter ->
                binding.daySpinner.adapter = adapter
            }
        };

        addLocationViewModel.mainContact.observe(viewLifecycleOwner, {
            binding.addLocationTitle.text = "Add location to " + it.firstname
        })

        binding.buttonAddLocation.setOnClickListener{
            // TODO: Get day number from spinner
            var day = 0
            var week = binding.weekNumberPicker.value
            var value = binding.description.text.toString()
            addLocationViewModel.addLocation(week, day, value)
            // TODO: Exit
        }
    }
}
