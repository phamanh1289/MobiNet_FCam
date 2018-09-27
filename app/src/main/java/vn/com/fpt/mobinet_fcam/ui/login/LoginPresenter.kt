package vn.com.fpt.mobinet_fcam.ui.login

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
class LoginPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<LoginContract.LoginView>(), LoginContract.LoginPresenter {

    override fun postLogin(map: HashMap<String, Any>) {
        addSubscribe(apiService.postLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.loadLogin(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }
}