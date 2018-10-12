package vn.com.fpt.mobinet_fcam.ui.contract.update.maintenance

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.com.fpt.mobinet_fcam.data.network.api.ApiHiOpennetService
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
class UpdateMaintenancePresenter @Inject constructor(private val apiService: ApiService, private val apiHiOpennetService: ApiHiOpennetService) : BasePresenter<UpdateMaintenanceContract.UpdateMaintenanceView>(), UpdateMaintenanceContract.UpdateMaintenancePresenter {

    override fun postUpdateContractMaintenance(map: HashMap<String, Any>) {
//        addSubscribe(apiService.postUpdateContractMaintenance(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ it ->
//                    view?.loadUpdateContractMaintenance(it)
//                }, {
//                    view?.handleError(it.message.toString())
//                }))
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

    override fun checkContractHiOpennet(map: HashMap<String, Any>) {
        addSubscribe(apiHiOpennetService.checkContractHiOpennet(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadContractHiOpennet(it)
                }, {
                    view?.handleError(it.message.toString())
                }))
    }


}