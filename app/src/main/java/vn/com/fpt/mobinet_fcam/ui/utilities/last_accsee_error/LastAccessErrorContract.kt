package vn.com.fpt.mobinet_fcam.ui.utilities.last_accsee_error

import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface LastAccessErrorContract {
    interface LastAccessErrorView : BaseView {
        fun loadLastAccessError(response: Any)
        fun handleError(response: String)
    }

    interface LastAccessErrorPresenter {
        fun getLastAccessError(obj : String)
    }
}