package vn.com.fpt.mobinet_fcam.ui.contract.list_result

import vn.com.fpt.mobinet_fcam.data.network.model.ResponseModel
import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface ListResultContract {
    interface DetailResultView : BaseView {
        fun loadMemberOfTeam(response: ResponseModel)
        fun loadListContract(response: ResponseModel)
        fun handleError(response: String)
    }

    interface DetailResultPresenter {
        fun getListMemberOfTeam(map: HashMap<String, Any>)
        fun getListContractDepl(map: HashMap<String, Any>)
        fun getListInfoContractMain(map: HashMap<String, Any>)
    }
}