package com.kpi.labs.lab3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kpi.labs.lab3.R
import com.kpi.labs.lab3.databinding.FragmentMainBinding
import com.kpi.labs.lab3.viewmodels.SharedViewModel

class MainFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            mainFragment = this@MainFragment
        }
    }

    fun navigateToResult() {
        if (sharedViewModel.isBrandsEmpty()) {
            Snackbar.make(
                binding.root,
                R.string.choose_brand,
                Snackbar.LENGTH_SHORT
            ).show()
            return
        }

        sharedViewModel.setUtensil(binding.utensilsSpinner.selectedItem.toString())
        sharedViewModel.createResult()
        findNavController().navigate(R.id.action_mainFragment_to_resultFragment)
    }

}