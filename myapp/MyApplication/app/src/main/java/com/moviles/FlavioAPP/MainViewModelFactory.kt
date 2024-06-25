package com.moviles.FlavioAPP

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moviles.FlavioAPP.data.api.ApiService

class MainViewModelFactory (
    private val application: Application,
    private val apiService: ApiService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application, apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}