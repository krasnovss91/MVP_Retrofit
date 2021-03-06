package com.example.mvp_retrofit.presenter

import android.util.Log
import com.example.mvp_retrofit.Contract
import com.example.mvp_retrofit.arch.ui
import com.example.mvp_retrofit.entity.Activity
import com.example.mvp_retrofit.entity.ActivityViewState
import com.example.mvp_retrofit.repository.MainRepository
import com.example.mvp_retrofit.view.MainActivity

class MainPresenter(
    private val view: Contract.View,
    private val repository: Contract.Repository
) : Contract.Presenter {

    private fun mapToActivity(activityViewState: ActivityViewState): Activity {
        return Activity(
            activityViewState.activity,
            activityViewState.type,
            activityViewState.participants,
            activityViewState.price,
            activityViewState.link,
            activityViewState.key,
            activityViewState.accessibility
        )
    }

    override fun load() {
        view.hideError()
        view.hideContent()
        view.showProgress()
        repository.load(::onSuccess, ::onError)
    }

    override fun reload() {
        load()
    }

    override fun onDelete(activityViewState: ActivityViewState) {
        repository.delete(mapToActivity(activityViewState))
        reload()
    }

    private fun onSuccess(lastActivity: Activity, activities: List<Activity>?) {
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

        ui {
            view.updateLastRequestInfo(lastActivity.activity)
            view.setContent(acivitiesViewState)
            view.hideProgress()
        }
    }

    private fun onError(throwable: Throwable) {
        ui {
            view.hideProgress()
            view.showError()
        }
    }

    companion object {
        fun create(mainActivity: MainActivity, mainRepository: MainRepository): MainPresenter {
            Log.d("create", "???????????? ???????????? create")
            return MainPresenter(mainActivity, mainRepository)
        }
    }
}