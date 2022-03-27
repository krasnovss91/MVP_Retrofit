package com.example.mvp_retrofit

import com.example.mvp_retrofit.entity.Activity
import com.example.mvp_retrofit.entity.ActivityViewState

interface Contract {

    interface Viev {

        fun showProgress()

        fun hideProgress()

        fun showError()

        fun hideError()

        fun hideContent()

        fun setContent(content: List<ActivityViewState>?)

        fun showContent(show: Boolean)

        fun showError(show: Boolean)

    }

    interface Presenter {
        fun load()
        fun reload()
    }

    interface Repository {

        fun load(): MutableList<Activity>?//может случиться, что из сети ничего не прилетит
        fun reload()

    }
}