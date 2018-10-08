package vn.com.fpt.mobinet_fcam.data.network.model

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class ResponseModel(val Code: Int, val Description: String, val Data: Any, val list: Any, val result: String) : BaseModel()