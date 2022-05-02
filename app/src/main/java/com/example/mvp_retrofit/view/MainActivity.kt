package com.example.mvp_retrofit.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_retrofit.Contract
import com.example.mvp_retrofit.R
import com.example.mvp_retrofit.entity.ActivityViewState
import com.example.mvp_retrofit.presenter.MainPresenter
import com.example.mvp_retrofit.repository.MainRepository
import com.example.mvp_retrofit.view.adapter.OnRequestSelected
import com.example.mvp_retrofit.view.adapter.RequestAdapter


const val TAG = "PROJECT_RETROFIT"

class MainActivity : AppCompatActivity(),
    Contract.View, OnRequestSelected {

    private val adapter = RequestAdapter(this)

    lateinit var requestList: RecyclerView
    lateinit var requestInfo: TextView
    lateinit var progress: ProgressBar
    lateinit var errorTitle: TextView
    lateinit var reloadButton: Button
    lateinit var requestButton: Button

    private val repository by lazy {
       MainRepository.create()
    }

    private val presenter by lazy {
        MainPresenter.create(this, repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SetUpViews()

        progress = findViewById(R.id.progress)
        errorTitle = findViewById(R.id.errorTitle)
        reloadButton = findViewById(R.id.reload)
        requestInfo = findViewById(R.id.requestInfo)


        requestButton = findViewById(R.id.requestButton)
        requestButton.setOnClickListener {
            presenter.load()
        }

        reloadButton.setOnClickListener {
            presenter.reload()
        }
    }

   private fun SetUpViews(){
      requestList = findViewById<RecyclerView>(R.id.requestList).apply {
         layoutManager = LinearLayoutManager(this@MainActivity)
         adapter = this@MainActivity.adapter
      }
   }

    override fun showProgress() {
        Log.d(TAG,"Внутри show progress")
        progress.isVisible = true
    }

    override fun hideProgress() {
        Log.d(TAG,"Внутри hide progress")
        progress.isVisible = false
    }

    override fun showError() {
        Log.d(TAG,"Внутри show error")
        showError(true)
    }

    override fun showError(show: Boolean) {
        errorTitle.isVisible = show
        reloadButton.isVisible = show
    }

    override fun hideError() {
        showError(false)
    }

    override fun hideContent() {
        showContent(false)
    }

    override fun setContent(content: List<ActivityViewState>?) {
        showContent(true)
        Log.d(TAG,"Внутри setContent $content")
        adapter.setRequests(content)
    }

    override fun updateLastRequestInfo(info: String) {
        requestInfo.text = "Last activity: $info"
    }

    override fun showContent(show: Boolean) {
        Log.d(TAG,"Внутри show content")
        requestList.isVisible = show
        requestButton.isVisible = show
        requestInfo.isVisible = show
    }

    override fun onSelected(requestViewState: ActivityViewState) {//здесь bottomSheet
//        TODO("Not yet implemented")
    }
}