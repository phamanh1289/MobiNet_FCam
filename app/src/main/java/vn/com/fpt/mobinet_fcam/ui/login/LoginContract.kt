package vn.com.fpt.mobinet_fcam.ui.login

import vn.com.fpt.mobinet_fcam.data.network.model.InfoUserModel
import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface LoginContract {
    interface LoginView : BaseView {
        fun loadLogin(response: InfoUserModel)
        fun handleError(response: String)
    }

    interface LoginPresenter {
        fun postLogin(map: HashMap<String,Any>)
    }
}