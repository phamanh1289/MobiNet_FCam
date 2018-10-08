package vn.com.fpt.mobinet_fcam.ui.main

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.com.fpt.mobinet_fcam.data.network.api.ApiService
import vn.com.fpt.mobinet_fcam.ui.base.BasePresenter
import vn.com.fpt.mobinet_fcam.utils.AppUtils
import javax.inject.Inject

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class MainActivityPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<MainActivityContract.MainView>(), MainActivityContract.MainPresenter {
    override fun getIpAddress() {
        addSubscribe(Observable.just("")
                .map { AppUtils.getExternalIp() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view?.loadIpAddress(it)
                }
        )
    }
}