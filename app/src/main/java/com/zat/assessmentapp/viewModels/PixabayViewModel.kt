package com.zat.assessmentapp.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zat.assessmentapp.models.PixabayMainModel
import com.zat.assessmentapp.repositories.DataRepository
import com.zat.assessmentapp.repositories.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PixabayViewModel @Inject constructor(private val dataRepository: DataRepository) :
    ViewModel() {

    val pixabayImagesResponseLiveData: MutableLiveData<NetworkResult<PixabayMainModel>>
        get() = dataRepository.pixabayImagesResponseLiveData


    fun getPixabayImages(
        context: Context, key: String,
        q: String,
        imageType: String,
        pretty: Boolean
    ) {
        viewModelScope.launch {
            try {
                dataRepository.getPixabayImages(context, key, q, imageType, pretty)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}