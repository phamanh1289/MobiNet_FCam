package vn.com.fpt.mobinet_fcam.ui.utilities.last_accsee_error

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
class LastAccessErrorPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<LastAccessErrorContract.LastAccessErrorView>(), LastAccessErrorContract.LastAccessErrorPresenter {

    override fun getLastAccessError(obj: String) {
        addSubscribe(apiService.getLastAccessError(obj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadLastAccessError(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }
}