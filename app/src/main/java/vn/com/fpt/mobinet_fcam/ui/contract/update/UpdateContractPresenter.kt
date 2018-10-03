package vn.com.fpt.mobinet_fcam.ui.contract.update

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
class UpdateContractPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<UpdateContractContract.UpdateContractView>(), UpdateContractContract.UpdateContractPresenter {

    override fun postUpdateContract(map: HashMap<String, Any>) {
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