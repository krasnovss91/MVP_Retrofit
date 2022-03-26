package com.example.mvp_retrofit.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Activity (
    @Json(name = "activity") val activity: String
        )

@JsonClass(generateAdapter = true)
data class ActivityViewState(
    @Json(name = "activity") val activity: String
)