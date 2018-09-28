package vn.com.fpt.mobinet_fcam.data.network.model

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
data class InfoUserModel(var mobiaccount: String = "", var password: String = "", var deptid: Int = 0, var subdeptid: Int = 0, var supinf: String = "", var description: String = "", var link: String = "") : BaseModel()