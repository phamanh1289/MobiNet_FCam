package vn.com.fpt.mobinet_fcam.ui.contract.search_list

import vn.com.fpt.mobinet_fcam.data.network.model.SearchListContractModel
import vn.com.fpt.mobinet_fcam.ui.base.BaseView

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface SearchListContract {
    interface SearchListView : BaseView {
        fun loadContractDeployment(response: SearchListContractModel)
        fun handleError(response: String)
    }

    interface SearchListPresenter {
        fun getContractDeployment(map: HashMap<String, Any>)
        fun getContractMaintenance(map: HashMap<String, Any>)
    }
}