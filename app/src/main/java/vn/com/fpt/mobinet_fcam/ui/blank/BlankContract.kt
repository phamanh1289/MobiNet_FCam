package vn.com.fpt.mobinet_fcam.ui.blank

import vn.com.fpt.mobinet_fcam.data.network.model.ResponseModel
import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface BlankContract {
    interface BlankView : BaseView {
        fun loadLogin(response: ResponseModel)
        fun handleError(response: String)
    }

    interface BlankPresenter {
        fun postLogin(map: HashMap<String,Any>)
    }
}