package com.zat.assessmentapp.repositories

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.zat.assessmentapp.R
import com.zat.assessmentapp.http.DataService
import com.zat.assessmentapp.models.PixabayMainModel
import com.zat.assessmentapp.utils.Helper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(private val dataService: DataService) {

    private suspend fun isNetworkAvailable(context: Context): Boolean {
        val isNetworkAvailable = withContext(Dispatchers.IO) {
            Helper.isNetworkAvailable(context)
        }

        return isNetworkAvailable
    }


    // Get Company List
    private val _pixabayImagesResponseLiveData =
        MutableLiveData<NetworkResult<PixabayMainModel>>()
    val pixabayImagesResponseLiveData: MutableLiveData<NetworkResult<PixabayMainModel>>
        get() = _pixabayImagesResponseLiveData

    suspend fun getPixabayImages(
        context: Context,
        key: String,
        q: String,
        imageType: String,
        pretty: Boolean
    ) {

        if (isNetworkAvailable(context)) {

            _pixabayImagesResponseLiveData.postValue(NetworkResult.Loading())
            val response = dataService.getPixabayImages(key,q,imageType,pretty)
            if (response.isSuccessful) {
                _pixabayImagesResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
            } else {
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _pixabayImagesResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
        } else {
            _pixabayImagesResponseLiveData.postValue(NetworkResult.Error(context.getString(R.string.network_not_available)))
        }
    }


}