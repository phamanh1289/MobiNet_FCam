package vn.com.fpt.mobinet_fcam.data.network.model

/**
 * *******************************************
 * * Created by AnhPT76 on 01/10/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
data class InfoContractModel(
        val id: Int,
        val objid: Int,
        val contract: String,
        val datecreate: String,
        var dateassign: String,
        val typecus: String,
        val priority: String,
        val fullname: String,
        val address: String,
        var appointmentdate: String,
        val totalchecklist: Int,
        val totalchecklistinmonth: Int,
        val hourremain: String
) : BaseModel()