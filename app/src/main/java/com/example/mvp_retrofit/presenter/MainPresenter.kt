package com.example.mvp_retrofit.presenter

import com.example.mvp_retrofit.Contract
import com.example.mvp_retrofit.repository.MainRepository

class MainPresenter(

private val view : Contract.Viev,
private val repository: Contract.Repository
) : Contract.Presenter {//здесь смапить Activity к ActivityViewState
    override fun load() {

        try {
            view.hideContent()
            view.showProgress()

            val activities = repository.load()



        } catch (e: Throwable){
          view.showProgress()
          view.showError()
        }
       /*
               try {

            view.hideContent()

            view.showProgress()

            val products = repository.load()

            val productsViewState: List<ProductViewState> = products.map { product ->
                ProductViewState(
                    product.avatar,
                    product.name,
                    product.producer,
                    product.cost,
                    product.id,
                    product.offsetDateTime
                )
            }
            view.setContent(productsViewState)
            view.hideProgress()
            view.showContent(true)
        } catch (e: Throwable) {

            view.showProgress()

            view.showError()
        }
        */
    }

    override fun reload() {
        load()
    }
}