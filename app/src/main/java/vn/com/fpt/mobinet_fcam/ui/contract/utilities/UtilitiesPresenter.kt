package vn.com.fpt.mobinet_fcam.ui.contract.utilities

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
class UtilitiesPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<UtilitiesContract.UtilitiesView>(), UtilitiesContract.UtilitiesPresenter {

//    override fun getContractDeployment(map: HashMap<String, Any>) {
//        addSubscribe(apiService.getContractDeployment(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ it ->
//                    view?.loadContractDeployment(it)
//                }, {
//                    view?.handleError(it.message.toString())
//                }))
//    }
}