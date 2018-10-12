package vn.com.fpt.mobinet_fcam.ui.contract.utilities

import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface UtilitiesContract {
    interface UtilitiesView : BaseView {
        fun handleError(response: String)
    }

    interface UtilitiesPresenter {
    }
}