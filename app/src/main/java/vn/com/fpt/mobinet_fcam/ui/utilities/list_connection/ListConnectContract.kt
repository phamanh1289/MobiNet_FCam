package vn.com.fpt.mobinet_fcam.ui.utilities.list_connection

import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface ListConnectContract {
    interface ListConnectView : BaseView {
        fun loadListConnection(response: Any)
        fun handleError(response: String)
    }

    interface ListConnectPresenter {
        fun getListConnection(objId : String)
    }
}