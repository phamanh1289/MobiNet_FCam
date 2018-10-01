package vn.com.fpt.mobinet_fcam.ui.contract.list_result

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
class ListResultPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<ListResultContract.DetailResultView>(), ListResultContract.DetailResultPresenter {

    override fun getListContractDepl(map: HashMap<String, Any>) {
        addSubscribe(apiService.getListContractDepl(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadListContract(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }
    override fun getListInfoContractMain(map: HashMap<String, Any>) {
        addSubscribe(apiService.getListContractMain(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadListContract(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }
}