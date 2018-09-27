package vn.com.fpt.mobinet_fcam.ui.main

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
class MainActivityPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<MainActivityContract.MainView>(), MainActivityContract.MainPresenter {
//    override fun getAssetToken(map: HashMap<String, Any>) {
//        addSubscribe(apiService.getAccessToken(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    view?.loadAssetToken(it)
//                }, {
//                    view?.handleError(it.message.toString())
//                }))
//    }
}