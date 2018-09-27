package vn.com.fpt.mobinet_fcam.ui.splash_screen

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.com.fpt.mobinet_fcam.data.network.api.ApiService
import vn.com.fpt.mobinet_fcam.ui.base.BasePresenter
import javax.inject.Inject

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class SplashScreenActivityPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<SplashScreenActivityContract.SplashScreenView>(), SplashScreenActivityContract.SplashScreenPresenter {
    override fun checkImei(map: HashMap<String, Any>) {
        addSubscribe(apiService.checkIMEI(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.loadCheckImei(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }
}