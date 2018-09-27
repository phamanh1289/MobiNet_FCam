package vn.com.fpt.mobinet_fcam.data.network.model

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
data class UserLoginModel(val userID: String, val simimei: String, val deviceIMEI: String, val isActive: Boolean, val isInsideAccount: String) : BaseModel()