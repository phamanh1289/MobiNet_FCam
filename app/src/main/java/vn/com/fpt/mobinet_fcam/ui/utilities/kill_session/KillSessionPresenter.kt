package vn.com.fpt.mobinet_fcam.ui.utilities.kill_session

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
class KillSessionPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<KillSessionContract.KillSessionView>(), KillSessionContract.KillSessionPresenter {

    override fun getKillSession(userName: String) {
        addSubscribe(apiService.getKillSession(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadKillSession(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }

    override fun toKillSession(objId: String) {
        addSubscribe(apiService.toKillSession(objId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadToKillSession(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }
}