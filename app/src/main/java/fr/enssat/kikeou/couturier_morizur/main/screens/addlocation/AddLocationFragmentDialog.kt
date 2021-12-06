package fr.enssat.kikeou.couturier_morizur.main.screens.addlocation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import fr.enssat.kikeou.couturier_morizur.KikeouApplication
import fr.enssat.kikeou.couturier_morizur.KikeouViewModelFactory
import fr.enssat.kikeou.couturier_morizur.R
import fr.enssat.kikeou.couturier_morizur.databinding.FragmentAddLocationBinding

class AddLocationFragmentDialog : DialogFragment() {
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

        // Set size of dialog
        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.90).toInt()
        dialog?.window?.setLayout(width, height)

        // Init fields
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

        // On click to add location
        binding.buttonAddLocation.setOnClickListener{
            var day = binding.daySpinner.selectedItem.toString()
            var week = binding.weekNumberPicker.value
            var value = binding.description.text.toString()
            addLocationViewModel.addLocation(day, week, value)
            // Close dialog
            dismiss()
        }
    }

    companion object {
        const val TAG = "AddLocationFragmentDialog"
    }
}
