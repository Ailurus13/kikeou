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
        KikeouViewModelFactory((activity?.application as KikeouApplication), null)
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
        var week = arguments?.getInt("week")
        dialog?.window?.setLayout(width, height)

        if(week == null) {
            // Init fields
            binding.weekNumberPicker.minValue = 1
            binding.weekNumberPicker.maxValue = 53
            binding.weekNumberPicker.visibility = View.VISIBLE
        }

        activity?.let {
            ArrayAdapter.createFromResource(it,
                R.array.days,
                R.layout.support_simple_spinner_dropdown_item
            ).also { adapter ->
                binding.daySpinner.adapter = adapter
            }
        };

        addLocationViewModel.mainContact.observe(viewLifecycleOwner, {
            if(week != null) {
                binding.addLocationTitle.text = "Add location to ${it.firstname} for week ${week.toString()}"
            } else {
                binding.addLocationTitle.text = "Add location to ${it.firstname}"
            }
        })

        // On click to add location
        binding.buttonAddLocation.setOnClickListener{
            var day = binding.daySpinner.selectedItemPosition
            var value = binding.description.text.toString()
            if(week != null) {
                addLocationViewModel.addLocation(day, week, value)
            } else {
                addLocationViewModel.addLocation(day, binding.weekNumberPicker.value, value)
            }
            // Close dialog
            dismiss()
        }
    }

    companion object {
        const val TAG = "AddLocationFragmentDialog"
    }
}
