package vn.com.fpt.mobinet_fcam.ui.utilities.reset_mac

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
class ResetMacPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<ResetMacContract.ResetMacView>(), ResetMacContract.ResetMacPresenter {

    override fun getMac(objID: String) {
        addSubscribe(apiService.getMac(objID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadMac(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }

    override fun resetMac(objID: String, newMac: String) {
        addSubscribe(apiService.resetMac(objID, newMac)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadResetMac(it.string())
                }, {
                    view?.handleError(it.message.toString())
                }))
    }
}