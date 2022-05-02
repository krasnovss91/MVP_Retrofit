package com.example.mvp_retrofit

import com.example.mvp_retrofit.entity.Activity
import com.example.mvp_retrofit.entity.ActivityViewState

interface Contract {

    interface View {

        fun showProgress()

        fun hideProgress()

        fun showError()

        fun hideError()

        fun hideContent()

        fun setContent(content: List<ActivityViewState>?)

        fun updateLastRequestInfo(info: String)

        fun showContent(show: Boolean)

        fun showError(show: Boolean)

    }

    interface Presenter {
        fun load()
        fun reload()
        fun onDelete(activityViewState: ActivityViewState)
    }

    interface Repository {

        fun load(
            onSuccess: (lastActivity: Activity, List<Activity>?) -> Unit,
            onError: (Throwable) -> Unit
        )

        fun reload(
            onSuccess: (lastActivity: Activity, List<Activity>?) -> Unit,
            onError: (Throwable) -> Unit
        )

        fun delete(activity: Activity)
    }
}