package vn.com.fpt.mobinet_fcam.ui.utilities.reset_password

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
class ResetPaswordPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<ResetPasswordContract.ResetPasswordView>(), ResetPasswordContract.ResetPasswordPresenter {

    override fun resetPassword(objID: String, newPass: String, ipAddress: String) {
        addSubscribe(apiService.resetPassword(objID, newPass,ipAddress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadResetPassword(it.string())
                }, {
                    view?.handleError(it.message.toString())
                }))
    }
}