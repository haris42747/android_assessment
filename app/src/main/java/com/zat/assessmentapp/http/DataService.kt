package com.zat.assessmentapp.http


import com.zat.assessmentapp.models.PixabayMainModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DataService {

    @GET("api/")
    suspend fun getPixabayImages(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("image_type") imageType: String,
        @Query("pretty") pretty: Boolean
    ): Response<PixabayMainModel>

}