package com.example.mvp_retrofit.repository

import android.util.Log
import com.example.mvp_retrofit.Contract
import com.example.mvp_retrofit.SimpleApi
import com.example.mvp_retrofit.entity.Activity
import com.example.mvp_retrofit.view.TAG
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class MainRepository : Contract.Repository {

    val activityList = mutableListOf<Activity>()

    override fun load(
        onSuccess: (lastActivity: Activity, List<Activity>?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        thread {
            try {

                val getActivityCall = NetworkModule.api.getActivity()

                val response = getActivityCall.execute()

                if (response.isSuccessful) {
                    val activity = response.body()!!
                    Log.d(TAG, activity.toString())
                    activityList.add(activity)
                    onSuccess(activity, activityList)
                } else {
                    val errorBody = response.errorBody()!!
                    Log.d(TAG, errorBody.toString())
                    throw RuntimeException(errorBody.toString())
                }
            } catch (t: Throwable) {
                Log.d(TAG, "Произошла ошибка: $t")
                onError(t)
            }
        }
    }

    override fun reload(
        onSuccess: (lastActivity: Activity, List<Activity>?) -> Unit,
        onError: (Throwable) -> Unit
    ) = load(onSuccess, onError)

    override fun delete(activity: Activity) {
        activityList.remove(activity)
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