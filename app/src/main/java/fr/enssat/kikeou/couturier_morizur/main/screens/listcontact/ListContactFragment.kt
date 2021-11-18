package fr.enssat.kikeou.couturier_morizur.main.screens.listcontact

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fr.enssat.kikeou.couturier_morizur.KikeouApplication
import fr.enssat.kikeou.couturier_morizur.KikeouViewModelFactory
import fr.enssat.kikeou.couturier_morizur.databinding.FragmentListContactBinding

class ListContactFragment : Fragment() {
    private var _binding: FragmentListContactBinding? = null
    private val binding get() = _binding!!

    private val listContactViewModel: ListContactViewModel by viewModels {
        KikeouViewModelFactory((activity?.application as KikeouApplication).contactRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListContactBinding.inflate(inflater, container, false)

        val adapter = ListContactAdapter()
        //adapter.data = listContactViewModel.listContact
        binding.contactList.adapter = adapter


        listContactViewModel.listContact.observe(viewLifecycleOwner, {
            adapter.data = it
            Log.v("enodebug", "${adapter.data}")
        })

        return binding.root
    }
}
