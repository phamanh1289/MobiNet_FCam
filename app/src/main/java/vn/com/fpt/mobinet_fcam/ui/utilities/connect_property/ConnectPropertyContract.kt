package vn.com.fpt.mobinet_fcam.ui.utilities.connect_property

import vn.com.fpt.mobinet_fcam.data.network.model.ConnectPropertyModel
import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface ConnectPropertyContract {
    interface ConnectPropertyView : BaseView {
        fun loadConnectProperty(response: ConnectPropertyModel)
        fun handleError(response: String)
    }

    interface ConnectPropertyPresenter {
        fun getConnectProperty(objId: String)
    }
}