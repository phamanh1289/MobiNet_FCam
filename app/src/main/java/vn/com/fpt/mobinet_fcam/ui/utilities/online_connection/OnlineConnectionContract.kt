package vn.com.fpt.mobinet_fcam.ui.utilities.online_connection

import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface OnlineConnectionContract {
    interface OnlineConnectionView : BaseView {
        fun loadOnlineConnection(response: Any)
        fun handleError(response: String)
    }

    interface OnlineConnectionPresenter {
        fun getOnlineConnection(obj : String)
    }
}