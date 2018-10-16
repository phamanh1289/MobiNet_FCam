package vn.com.fpt.mobinet_fcam.ui.utilities.reset_password

import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface ResetPasswordContract {
    interface ResetPasswordView : BaseView {
        fun loadResetPassword(response: String)
        fun handleError(response: String)
    }

    interface ResetPasswordPresenter {
        fun resetPassword(objID: String, newPass: String, ipAddress: String)
    }
}