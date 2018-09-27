package vn.com.fpt.mobinet_fcam.data.network.model

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
data class InfoUserModel(val mobiaccount: String, val password: String, val deptid: Int, val subdeptid: Int, val supinf: String, val description: String, val link: String) : BaseModel()