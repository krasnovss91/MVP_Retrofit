package com.example.mvp_retrofit.view

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_retrofit.Contract
import com.example.mvp_retrofit.R
import com.example.mvp_retrofit.entity.ActivityViewState

class MainActivity : AppCompatActivity(), Contract.Viev {//activity не должно ничего знать о преобразованиях

    lateinit var requestList:RecyclerView
    lateinit var requestInfo:TextView
    lateinit var progress: ProgressBar
    lateinit var errorTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {//дёргает методы presenter, все преобразования там
        super.onCreate(savedInstanceState)//в сеть идут методы repository, которые вызывают методы presenter
        setContentView(R.layout.activity_main)
    }

    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun hideProgress() {
        TODO("Not yet implemented")
    }

    override fun showError() {
        TODO("Not yet implemented")
    }

    override fun showError(show: Boolean) {
        TODO("Not yet implemented")
    }

    override fun hideError() {
        TODO("Not yet implemented")
    }

    override fun hideContent() {
        TODO("Not yet implemented")
    }

    override fun setContent(content: List<ActivityViewState>?) {
        TODO("Not yet implemented")
    }

    override fun showContent(show: Boolean) {
        TODO("Not yet implemented")
    }
}