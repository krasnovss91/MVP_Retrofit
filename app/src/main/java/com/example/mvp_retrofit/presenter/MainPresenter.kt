package com.example.mvp_retrofit.presenter

import android.util.Log
import com.example.mvp_retrofit.Contract
import com.example.mvp_retrofit.entity.ActivityViewState
import com.example.mvp_retrofit.repository.MainRepository
import com.example.mvp_retrofit.view.MainActivity

class MainPresenter(

    private val view: Contract.View,
    private val repository: Contract.Repository
) : Contract.Presenter {

    override fun load() {
        Log.d("load","Внутри метода Load")
        try {
            view.hideContent()
            view.showProgress()

            val activities = repository.load()

            val acivitiesViewState: List<ActivityViewState>? = activities?.map { activity ->
                ActivityViewState(
                    activity.activity,
                    activity.type,
                    activity.participants,
                    activity.price,
                    activity.link,
                    activity.key,
                    activity.accessibility
                )
            }
            view.setContent(acivitiesViewState)
            view.hideProgress()
            view.showContent(true)

        } catch (e: Throwable) {
            view.showProgress()
            view.showError()
        }
    }

    override fun reload() {
        load()
    }

    companion object {
        fun create(mainActivity: MainActivity, mainRepository: MainRepository):MainPresenter {
            Log.d("create","внутри метода create")
           return MainPresenter(mainActivity, mainRepository)
        }
    }
}