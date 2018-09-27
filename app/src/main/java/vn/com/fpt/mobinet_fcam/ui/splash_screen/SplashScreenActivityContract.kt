package vn.com.fpt.mobinet_fcam.ui.splash_screen

import vn.com.fpt.mobinet_fcam.data.network.model.ResponseModel
import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface SplashScreenActivityContract {
    interface SplashScreenView : BaseView {
        fun handleError(error: String)
        fun loadCheckImei(response: ResponseModel)
    }

    interface SplashScreenPresenter {
        fun checkImei(map: HashMap<String, Any>)
    }
}