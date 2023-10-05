package com.mlbench.probau.viewModels

import android.app.Application
import android.net.NetworkRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class NetworkViewModelFactory @Inject constructor(val application: Application, val networkRequest: NetworkRequest):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NetworkViewModel::class.java)) {
            return NetworkViewModel(application, networkRequest) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}