package vn.com.fpt.mobinet_fcam.ui.contract.detail

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
class DetailContractPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<DetailContractContract.DetailContractView>(), DetailContractContract.DetailContractPresenter {

    override fun getDetailDeployment(map: HashMap<String, Any>) {
        addSubscribe(apiService.getDetailContractDepl(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadDetailContract(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }

    override fun getDetailMaintenance(map: HashMap<String, Any>) {
        addSubscribe(apiService.getDetailContractMain(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadDetailContract(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }
}