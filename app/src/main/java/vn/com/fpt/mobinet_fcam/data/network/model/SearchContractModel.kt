package vn.com.fpt.mobinet_fcam.data.network.model

/**
 * *******************************************
 * * Created by AnhPT76 on 12/10/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
data class SearchContractModel(
        var objid: Int = 0,
        var fullname: String = "",
        var username: String = "",
        var servicetype: String = "",
        var status: String = "",
        var contract: String = "",
        var location: String = ""
) : BaseModel()