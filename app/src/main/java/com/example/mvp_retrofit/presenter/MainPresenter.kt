package com.example.mvp_retrofit.presenter

import com.example.mvp_retrofit.Contract

class MainPresenter : Contract.Presenter {//здесь смапить Activity к ActivityViewState
    override fun load() {
        TODO("Not yet implemented")
    }

    override fun reload() {
        load()
    }
}