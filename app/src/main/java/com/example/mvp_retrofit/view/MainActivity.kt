package com.example.mvp_retrofit.view

import android.os.Bundle
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
import com.example.mvp_retrofit.view.adapter.RequestAdapter

class MainActivity : AppCompatActivity(),
    Contract.Viev {//activity не должно ничего знать о преобразованиях

    private val adapter = RequestAdapter()

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

    override fun onCreate(savedInstanceState: Bundle?) {//дёргает методы presenter, все преобразования там
        super.onCreate(savedInstanceState)//в сеть идут методы repository, которые вызывают методы presenter
        setContentView(R.layout.activity_main)

        progress = findViewById(R.id.progress)
        errorTitle = findViewById(R.id.errorTitle)
        reloadButton = findViewById(R.id.reload)

        presenter.load()

        requestButton.setOnClickListener {

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
        progress.isVisible = true
    }

    override fun hideProgress() {
        progress.isVisible = false
    }

    override fun showError() {
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
        //adapter.setProducts(content)
    }

    override fun showContent(show: Boolean) {
        requestList.isVisible = show
        requestButton.isVisible = show
    }
}