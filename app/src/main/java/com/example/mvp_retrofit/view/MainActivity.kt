package com.example.mvp_retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvp_retrofit.R

class MainActivity : AppCompatActivity() {//activity не должно ничего знать о преобразованиях
    override fun onCreate(savedInstanceState: Bundle?) {//дёргает методы presenter, все преобразования там
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}