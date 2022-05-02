package com.example.mvp_retrofit.arch

import android.os.Handler
import android.os.Looper

private val handler = Handler(Looper.getMainLooper())

fun ui(action: () -> Unit) = handler.post(action)
