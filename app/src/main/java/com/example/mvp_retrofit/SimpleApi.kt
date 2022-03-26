package com.example.mvp_retrofit

import com.example.mvp_retrofit.entity.Activity
import retrofit2.Call
import retrofit2.http.GET

interface SimpleApi {
    @GET("activity")
    fun getActivity(): Call<Activity>
}