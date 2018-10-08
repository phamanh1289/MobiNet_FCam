package vn.com.fpt.mobinet_fcam.ui.main

import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface MainActivityContract {
    interface MainView : BaseView {
        fun loadIpAddress(data: String)
        fun handleError(error: String)
    }

    interface MainPresenter {
        fun getIpAddress()
    }
}