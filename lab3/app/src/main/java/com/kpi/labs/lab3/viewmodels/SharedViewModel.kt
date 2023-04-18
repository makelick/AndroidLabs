package com.kpi.labs.lab3.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kpi.labs.lab3.R

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private var selectedBrands = MutableLiveData<List<String>>()

    private var utensil = MutableLiveData<String>()

    private var _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    init {
        selectedBrands.value = emptyList()
        utensil.value = ""
    }

    fun createResult() {
        _result.value = getApplication<Application>().resources.getString(
            R.string.result,
            utensil.value,
            selectedBrands.value?.joinToString(", ")
        )
    }

    fun setUtensil(newUtensil: String) {
        utensil.value = newUtensil
    }

    fun isBrandsEmpty(): Boolean {
        return selectedBrands.value.isNullOrEmpty()
    }

    fun addBrand(brand: String) {
        if (selectedBrands.value?.contains(brand) == false) {
            selectedBrands.value = (selectedBrands.value.orEmpty() + brand).sorted()
        }
    }

    fun removeBrand(brand: String) {
        selectedBrands.value = selectedBrands.value?.filter { it != brand }
    }

}