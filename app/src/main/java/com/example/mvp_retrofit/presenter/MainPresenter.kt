package com.example.mvp_retrofit.presenter

import com.example.mvp_retrofit.Contract
import com.example.mvp_retrofit.entity.ActivityViewState

class MainPresenter(

    private val view: Contract.Viev,
    private val repository: Contract.Repository
) : Contract.Presenter {
    //здесь смапить Activity к ActivityViewState
    override fun load() {

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
}