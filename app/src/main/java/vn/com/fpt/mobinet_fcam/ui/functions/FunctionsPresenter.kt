package vn.com.fpt.mobinet_fcam.ui.functions

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
class FunctionsPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<FunctionsContract.FunctionsView>(), FunctionsContract.FunctionsPresenter {
    override fun postSearchContract(content: String, type: String) {
        addSubscribe(apiService.getInfoContract(content, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadSearchContract(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }

}