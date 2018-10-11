package vn.com.fpt.mobinet_fcam.ui.contract.update.maintenance

import okhttp3.ResponseBody
import vn.com.fpt.mobinet_fcam.data.network.model.HiOpenNetModel
import vn.com.fpt.mobinet_fcam.data.network.model.ResponseModel
import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface UpdateMaintenanceContract {
    interface UpdateMaintenanceView : BaseView {
        fun loadContractHiOpennet(response: HiOpenNetModel)
        fun loadUpdateContractMaintenance(response: ResponseModel)
        fun loadDetailUpdate(response: ResponseBody)
        fun handleError(response: String)
    }

    interface UpdateMaintenancePresenter {
        fun checkContractHiOpennet(map: HashMap<String, Any>)
        fun postUpdateContractMaintenance(map: HashMap<String, Any>)
        fun getMaintenanceObject(userName: String, passWord: String, mainId: Int, objId: Int)
    }
}