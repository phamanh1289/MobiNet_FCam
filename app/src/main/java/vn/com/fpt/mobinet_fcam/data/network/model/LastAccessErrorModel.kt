package vn.com.fpt.mobinet_fcam.data.network.model

/**
 * *******************************************
 * * Created by AnhPT76 on 16/10/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
data class LastAccessErrorModel(
        var errDate: String = "",
        var errCode: String = "",
        var errDescription: String = "",
        var errCallerId: String = "",
        var errAdditional: String = ""
) : BaseModel()