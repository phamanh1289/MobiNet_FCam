package vn.com.fpt.mobinet_fcam.ui.contract.search_list

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
class SearchListPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<SearchListContract.SearchListView>(), SearchListContract.SearchListPresenter {

    override fun getContractDeployment(map: HashMap<String, Any>) {
        addSubscribe(apiService.getContractDeployment(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadContractDeployment(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }
    override fun getContractMaintenance(map: HashMap<String, Any>) {
        addSubscribe(apiService.getContractMaintenance(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadContractDeployment(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }
}