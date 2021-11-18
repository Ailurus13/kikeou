package fr.enssat.kikeou.couturier_morizur.main.screens.listcontact

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import fr.enssat.kikeou.couturier_morizur.KikeouApplication
import fr.enssat.kikeou.couturier_morizur.KikeouViewModelFactory
import fr.enssat.kikeou.couturier_morizur.R
import fr.enssat.kikeou.couturier_morizur.databinding.FragmentListContactBinding
import fr.enssat.kikeou.couturier_morizur.databinding.FragmentMainContactBinding
import fr.enssat.kikeou.couturier_morizur.main.screens.maincontact.MainContactViewModel

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var lca = ListContactAdapter()
        binding.contactList.adapter = lca

        listContactViewModel.listContact.observe(viewLifecycleOwner, {
            lca.data = it
            Log.i("enodebug", "${lca.data}")
        })
    }
}
