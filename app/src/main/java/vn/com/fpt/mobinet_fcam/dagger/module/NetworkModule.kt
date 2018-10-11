package vn.com.fpt.mobinet_fcam.dagger.module

import android.app.Application
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import vn.com.fpt.mobinet_fcam.dagger.connect.ApiConfig
import vn.com.fpt.mobinet_fcam.dagger.connect.ApiConfigType
import vn.com.fpt.mobinet_fcam.dagger.scope.AppScope
import vn.com.fpt.mobinet_fcam.data.network.api.ApiHiOpennetService
import vn.com.fpt.mobinet_fcam.data.network.api.ApiService
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
@Module
@AppScope
class NetworkModule(private val mType: ApiConfigType) {
    private val CACHE_SIZE_BUFFER = (50 * 1024 * 1024).toLong()
    private val httpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(4, TimeUnit.MINUTES)
            .writeTimeout(4, TimeUnit.MINUTES)
            .addInterceptor(ConnectivityInterceptor())
            .retryOnConnectionFailure(true)
            .addInterceptor(ApiCustomInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

    @Provides
    fun provideOkHttpClient(app: Application): OkHttpClient {
        val cacheDir = File(app.cacheDir, "http")
        return OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .cache(okhttp3.Cache(cacheDir, CACHE_SIZE_BUFFER))
                .retryOnConnectionFailure(true)
                .build()
    }

    @Provides
    fun provideApiService(): ApiService {
        val retrofit = Retrofit.Builder()
                .baseUrl(ApiConfig.createConnectionDetail(mType).baseURL)
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                        .setLenient()
                        .create()))
                .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideApiHiOpennetService(): ApiHiOpennetService {
        val retrofit = Retrofit.Builder()
                .baseUrl(ApiConfig.createConnectionDetail(mType).baseURLHiApi)
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                        .setLenient()
                        .create()))
                .build()
        return retrofit.create(ApiHiOpennetService::class.java)
    }

}