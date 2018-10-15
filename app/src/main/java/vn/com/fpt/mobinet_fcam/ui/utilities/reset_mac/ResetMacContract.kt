package vn.com.fpt.mobinet_fcam.ui.utilities.reset_mac

import vn.com.fpt.mobinet_fcam.data.network.model.MacModel
import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface ResetMacContract {
    interface ResetMacView : BaseView {
        fun loadMac(response: MacModel)
        fun loadResetMac(response: String)
        fun handleError(response: String)
    }

    interface ResetMacPresenter {
        fun getMac(objID : String)
        fun resetMac(objID : String, newMac : String)
    }
}