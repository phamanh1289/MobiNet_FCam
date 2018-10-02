package vn.com.fpt.mobinet_fcam.ui.contract.detail

import vn.com.fpt.mobinet_fcam.data.network.model.DetailContractModel
import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface DetailContractContract {
    interface DetailContractView : BaseView {
        fun loadDetailContract(response: DetailContractModel)
        fun handleError(response: String)
    }

    interface DetailContractPresenter {
        fun getDetailDeployment(map: HashMap<String, Any>)
        fun getDetailMaintenance(map: HashMap<String, Any>)
    }
}