package vn.com.fpt.mobinet_fcam.ui.utilities.online_connection

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
class OnlineConnectionPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<OnlineConnectionContract.OnlineConnectionView>(), OnlineConnectionContract.OnlineConnectionPresenter {

    override fun getOnlineConnection(obj : String) {
        addSubscribe(apiService.getOnlineConnection(obj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadOnlineConnection(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }
}