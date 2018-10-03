package vn.com.fpt.mobinet_fcam.ui.contract.update

import vn.com.fpt.mobinet_fcam.data.network.model.ResponseModel
import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface UpdateContractContract {
    interface UpdateContractView : BaseView {
        fun loadUpdateContract(response: ResponseModel)
        fun handleError(response: String)
    }

    interface UpdateContractPresenter {
        fun postUpdateContract(map: HashMap<String,Any>)
    }
}