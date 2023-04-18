package com.kpi.labs.lab3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CompoundButton
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

        val checkboxes = listOf(
            binding.ikeaCheckbox,
            binding.cutipolCheckbox,
            binding.iittalaCheckbox
        )

        checkboxes.forEach {
            it.setOnCheckedChangeListener { checkbox, isChecked ->
                changeBrandStatus(checkbox, isChecked)
            }
        }

        binding.utensilsSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    sharedViewModel.setUtensil("")
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    sharedViewModel.setUtensil(parent?.getItemAtPosition(position).toString())
                }
            }

        binding.okButton.setOnClickListener {
            navigateToResult()
        }

    }

    private fun changeBrandStatus(compoundButton: CompoundButton, isChecked: Boolean) {
        val brand = compoundButton.text.toString()
        if (isChecked) {
            sharedViewModel.addBrand(brand)
        } else {
            sharedViewModel.removeBrand(brand)
        }
    }

    private fun navigateToResult() {
        if (sharedViewModel.isBrandsEmpty()) {
            Snackbar.make(
                binding.root,
                R.string.choose_brand,
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            sharedViewModel.saveResult()
            findNavController().navigate(R.id.action_mainFragment_to_resultFragment)
        }
    }

}