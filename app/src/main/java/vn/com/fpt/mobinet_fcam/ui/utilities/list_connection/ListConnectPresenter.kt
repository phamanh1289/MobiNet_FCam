package vn.com.fpt.mobinet_fcam.ui.utilities.list_connection

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
class ListConnectPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<ListConnectContract.ListConnectView>(), ListConnectContract.ListConnectPresenter {

    override fun getListConnection(objId : String) {
        addSubscribe(apiService.getListConnection(objId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadListConnection(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }
}