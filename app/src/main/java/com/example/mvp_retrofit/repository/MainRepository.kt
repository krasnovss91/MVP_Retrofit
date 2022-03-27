package com.example.mvp_retrofit.repository

import android.util.Log
import com.example.mvp_retrofit.Contract
import com.example.mvp_retrofit.SimpleApi
import com.example.mvp_retrofit.entity.Activity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class MainRepository : Contract.Repository {
    override fun load(): MutableList<Activity>? {//идём в сеть здесь. Ловим объекты и кладём в список

        val activityList: MutableList<Activity>? = null
        thread {
            val getActivityCall = NetworkModule.api.getActivity()

            val response = getActivityCall.execute()
            if (response.isSuccessful) {

            } else {
                val errorBody = response.errorBody()!!
                Log.d("ACTIVITY",errorBody.toString())
            }
        }
        return activityList
    }

    override fun reload() {
        load()
    }

    object NetworkModule {
        private val client = OkHttpClient().newBuilder()
            .readTimeout(30000, TimeUnit.MILLISECONDS)
            .connectTimeout(30000, TimeUnit.MILLISECONDS)
            .writeTimeout(30000, TimeUnit.MILLISECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        private val retrofit = Retrofit.Builder()
            .baseUrl("https://www.boredapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()

        val api = retrofit.create<SimpleApi>()
    }

    companion object {
        fun create() = MainRepository()
    }
}