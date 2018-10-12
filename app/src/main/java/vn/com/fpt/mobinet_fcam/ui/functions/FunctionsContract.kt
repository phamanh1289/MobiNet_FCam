package vn.com.fpt.mobinet_fcam.ui.functions

import vn.com.fpt.mobinet_fcam.data.network.model.SearchContractModel
import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface FunctionsContract {
    interface FunctionsView : BaseView {
        fun loadSearchContract(response: SearchContractModel)
        fun handleError(response: String)
    }

    interface FunctionsPresenter {
        fun postSearchContract(content: String, type: String)
    }
}