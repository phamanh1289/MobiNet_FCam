package vn.com.fpt.mobinet_fcam.data.network.model

/**
 * *******************************************
 * * Created by AnhPT76 on 04/10/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
data class UpdateContractModel(
        var deployid: Int,
        var objid: Int,
        var indoor: Int,
        var outdoor: Int,
        var indtype: Int,
        var outdtype: Int,
        var cabletype: Int,
        var box: Int,
        var router: Int,
        var routeramount: Int,
        var boxlink: Int,
        var wire: Int,
        var aluminumtag: Int,
        var mangxoong01fo: Int,
        var button: Int,
        var jumperwire: Int,
        var stickingplaster: Int,
        var onu: Int,
        var boxftth: Int,
        var tube: Int,
        var opticaljump: Int,
        var fastconnector: Int,
        var fastconnectorapc: Int,
        var opticalfiber: Int,
        var modem: Int,
        var modemamount: Int,
        var modemtype: Int,
        var stb: Int,
        var stbamount: Int,
        var stbtype: Int,
        var odccabletype: String,
        var note: String,
        var assigndate: String,
        var assigndate1: String,
        var reasondelay: Int,
        var notereasondelay: String,
        var status: Int,
        var scsc: Int,
        var custype: Int,
        var appointment: Int
) : BaseModel()