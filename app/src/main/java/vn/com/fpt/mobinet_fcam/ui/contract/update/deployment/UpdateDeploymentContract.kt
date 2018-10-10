package vn.com.fpt.mobinet_fcam.ui.contract.update.deployment

import okhttp3.ResponseBody
import vn.com.fpt.mobinet_fcam.data.network.model.ResponseModel
import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface UpdateDeploymentContract {
    interface UpdateContractView : BaseView {
        fun loadUpdateContractDeployment(response: ResponseModel)
        fun loadDetailUpdate(response: ResponseBody)
        fun handleError(response: String)
    }

    interface UpdateContractPresenter {
        fun postUpdateContractDeployment(map: HashMap<String, Any>)
        fun getDetailUpdate(userName: String, passWord: String, deplId: Int, objId: Int)
        fun getMaintenanceObject(userName: String, passWord: String, mainId: Int, objId: Int)
    }
}