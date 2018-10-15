package vn.com.fpt.mobinet_fcam.ui.utilities.kill_session

import okhttp3.ResponseBody
import vn.com.fpt.mobinet_fcam.data.network.model.KillSessionModel
import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface KillSessionContract {
    interface KillSessionView : BaseView {
        fun loadKillSession(response: KillSessionModel)
        fun loadToKillSession(response: ResponseBody)
        fun handleError(response: String)
    }

    interface KillSessionPresenter {
        fun getKillSession(userName: String)
        fun toKillSession(objId: String)
    }
}