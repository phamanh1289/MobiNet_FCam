package vn.com.fpt.mobinet_fcam.ui.utilities.connect_property

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
class ConnectPropertyPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<ConnectPropertyContract.ConnectPropertyView>(), ConnectPropertyContract.ConnectPropertyPresenter {

    override fun getConnectProperty(objId : String) {
        addSubscribe(apiService.getConnectionProperties(objId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadConnectProperty(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }
}