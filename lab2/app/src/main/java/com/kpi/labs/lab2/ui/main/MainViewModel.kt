package com.kpi.labs.lab2.ui.main

import android.app.Application
import android.widget.CompoundButton
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kpi.labs.lab2.R

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private var _selectedBrands = MutableLiveData<List<String>>()
    val selectedBrands: LiveData<List<String>> = _selectedBrands

    private var _utensil = MutableLiveData<String>()
    val utensil: LiveData<String> = _utensil

    private var _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    init {
        _selectedBrands.value = emptyList()
        _utensil.value = ""
    }

    fun createResult() {
        _result.value = getApplication<Application>().resources.getString(
            R.string.result,
            utensil.value,
            selectedBrands.value?.joinToString(", ")
        )
    }

    fun setUtensil(utensil: String) {
        _utensil.value = utensil
    }

    fun isBrandsEmpty(): Boolean {
        return _selectedBrands.value.isNullOrEmpty()
    }

    fun changeBrandStatus(compoundButton: CompoundButton, isChecked: Boolean) {
        val brand = compoundButton.text.toString()
        if (isChecked) {
            _selectedBrands.value = _selectedBrands.value.orEmpty() + brand
        } else {
            _selectedBrands.value = _selectedBrands.value?.filter { it != brand }
        }
    }

}