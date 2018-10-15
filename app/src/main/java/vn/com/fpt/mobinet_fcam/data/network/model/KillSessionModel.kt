package vn.com.fpt.mobinet_fcam.data.network.model

/**
 * *******************************************
 * * Created by AnhPT76 on 15/10/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
data class KillSessionModel(
        var date: String = "",
        var name: String = "",
        var nasname: String = "",
        var ipaddress: String = "",
        var callerid: String = ""
) : BaseModel()