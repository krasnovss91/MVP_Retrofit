package com.example.mvp_retrofit.arch

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

interface BaseView

abstract class BaseActivity<P: BasePresenter<out BaseView>>: AppCompatActivity(), BaseView {
    protected abstract var p: P
}

abstract class BasePresenter<V: BaseView> {
    protected lateinit var view: V

    abstract fun attach(v: V)
    abstract fun detach(v: V)
}


interface Contract {
    interface View: BaseView {
        //
    }
}

class StorePresenter: BasePresenter<Contract.View>() {

    override fun attach(v: Contract.View) {
        view = v
    }

    override fun detach(v: Contract.View) {
    }

}

class StoreActivity: BaseActivity<StorePresenter>(), Contract.View {
    override var p: StorePresenter = StorePresenter()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        p.attach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        p.detach(this)
    }
}