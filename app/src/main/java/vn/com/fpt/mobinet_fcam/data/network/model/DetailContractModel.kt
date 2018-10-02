package vn.com.fpt.mobinet_fcam.data.network.model

/**
 * *******************************************
 * * Created by AnhPT76 on 02/10/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
data class DetailContractModel(
        val deployid: Int,
        val objid: Int,
        val contact: String,
        val fullname: String,
        val accessname: String,
        val address: String,
        val servicetype: String,
        val assigndate: String,
        val macaddress: String,
        val area: String,
        val createat: String,
        val createby: String,
        val image: String,
        val cusnote: String
) : BaseModel()