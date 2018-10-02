package vn.com.fpt.mobinet_fcam.ui.image.view_image

import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface ViewImageContract {
    interface ViewImageView : BaseView {
        fun loadImage(response: String)
        fun handleError(response: String)
    }

    interface ViewImagePresenter {
        fun getImage(id:String)
    }
}