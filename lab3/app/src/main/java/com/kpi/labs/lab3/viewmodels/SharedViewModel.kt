package com.kpi.labs.lab3.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kpi.labs.lab3.Lab3Application
import com.kpi.labs.lab3.R
import com.kpi.labs.lab3.database.Result
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val database = (application as Lab3Application).database

    private var selectedBrands = MutableLiveData<List<String>>()

    private var utensil = MutableLiveData<String>()

    private var _stringResult = MutableLiveData<String>()
    val stringResult: LiveData<String> = _stringResult

    init {
        selectedBrands.value = emptyList()
        utensil.value = ""
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

    fun saveResult() {

        createStringResult()

        val res = Result(
            id = 0,
            utensil = utensil.value.orEmpty(),
            brands = getBrandsAsString()
        )

        viewModelScope.launch {
            database.getDao().insert(res)
        }
    }

    private fun createStringResult() {
        _stringResult.value = getApplication<Application>().resources.getString(
            R.string.result,
            utensil.value,
            getBrandsAsString()
        )
    }

    private fun getBrandsAsString(): String {
        return selectedBrands.value?.joinToString(", ") ?: ""
    }

}