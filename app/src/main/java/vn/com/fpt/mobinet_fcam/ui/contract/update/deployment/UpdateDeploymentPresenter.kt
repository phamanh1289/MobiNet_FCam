package vn.com.fpt.mobinet_fcam.ui.contract.update.deployment

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
class UpdateDeploymentPresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<UpdateDeploymentContract.UpdateContractView>(), UpdateDeploymentContract.UpdateContractPresenter {

    override fun postUpdateContractDeployment(map: HashMap<String, Any>) {
//        addSubscribe(apiService.postUpdateContractDeployment(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ it ->
//                    view?.loadUpdateContractDeployment(it)
//                }, {
//                    view?.handleError(it.message.toString())
//                }))
    }

    override fun getDetailUpdate(userName: String, passWord: String, deplId: Int, objId: Int) {
        addSubscribe(apiService.getDetailContract(userName, passWord, deplId, objId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadDetailUpdate(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }

    override fun getMaintenanceObject(userName: String, passWord: String, mainId: Int, objId: Int) {
        addSubscribe(apiService.getMaintenanceObject(userName, passWord, mainId, objId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadDetailUpdate(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }
}