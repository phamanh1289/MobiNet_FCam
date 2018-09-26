package vn.com.fpt.mobinet_fcam.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
open class BasePresenter<V : BaseView> : IPresenter<V> {

    var view: V? = null
    private val compositeDisposable: CompositeDisposable?

    override val isViewAttach: Boolean
        get() = view != null

    init {
        this.compositeDisposable = CompositeDisposable()
    }

    override fun onAttach(mvpView: V) {
        this.view = mvpView
    }

    override fun onDetach() {
        this.view = null
        unsubscribe()
    }

    private fun unsubscribe() {
        compositeDisposable?.dispose()
    }

    protected fun addSubscribe(disposable: Disposable) {
        compositeDisposable?.add(disposable)
    }
}