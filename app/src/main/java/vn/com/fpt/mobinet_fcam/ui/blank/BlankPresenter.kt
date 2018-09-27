package vn.com.fpt.mobinet_fcam.ui.login

import vn.com.fpt.mobinet_fcam.data.network.api.ApiService
import vn.com.fpt.mobinet_fcam.ui.base.BasePresenter
import vn.com.fpt.mobinet_fcam.ui.blank.BlankContract
import javax.inject.Inject

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class BlankPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<BlankContract.BlankView>(), BlankContract.BlankPresenter {

    override fun postLogin(map: HashMap<String, Any>) {
//        addSubscribe(apiService.postLogin(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ it ->
//                    view?.loadLogin(it)
//                }, {
//                    view?.handleError(it.message.toString())
//                }))
    }
}