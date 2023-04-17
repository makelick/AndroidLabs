package com.kpi.labs.lab1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.kpi.labs.lab1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val selectedBrands: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ikeaCheckbox.setOnCheckedChangeListener(::onCheckboxClicked)
        binding.cutipolCheckbox.setOnCheckedChangeListener(::onCheckboxClicked)
        binding.iittalaCheckbox.setOnCheckedChangeListener(::onCheckboxClicked)

        binding.okButton.setOnClickListener {
            if (selectedBrands.isEmpty()) {
                Snackbar.make(
                    binding.root,
                    R.string.choose_brand,
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val utensil = binding.utensilsSpinner.selectedItem.toString()
            binding.resultText.text =
                getString(R.string.result, utensil, selectedBrands.joinToString(", "))
        }

    }

    private fun onCheckboxClicked( checkBox: CompoundButton, isChecked: Boolean) {
        if (isChecked) {
            selectedBrands.add(checkBox.text.toString())
        } else {
            selectedBrands.remove(checkBox.text.toString())
        }
    }

}

