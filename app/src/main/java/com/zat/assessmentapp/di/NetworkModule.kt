package com.zat.assessmentapp.di


import android.app.Application
import android.net.NetworkRequest
import com.mlbench.probau.viewModels.NetworkViewModelFactory
import com.zat.assessmentapp.http.AuthInterceptor
import com.zat.assessmentapp.http.DataService
import com.zat.assessmentapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(provideOkHttpClient(AuthInterceptor()))
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideNetworkViewModelFactory(
        application: Application,
        networkRequest: NetworkRequest
    ): NetworkViewModelFactory {
        return NetworkViewModelFactory(application, networkRequest)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES).build()
    }

    @Singleton
    @Provides
    fun providesNetworkAPI(retrofitBuilder: Retrofit.Builder): DataService {
        return retrofitBuilder.build().create(DataService::class.java)
    }

}