package vn.com.fpt.mobinet_fcam.data.network.model

/**
 * *******************************************
 * * Created by AnhPT76 on 12/10/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
data class ConnectPropertyModel(
        var admin: String? = "",
        var operation: String? = "",
        var downSNR: String? = "",
        var upsnr: String? = "",
        var downAtt: String? = "",
        var upAtt: String? = "",
        var modeADSL: String? = "",
        var downStream: String? = "",
        var upStream: String? = "",
        var upTime: String? = "",
        var macFilter: String? = "",
        var localType: String? = "",
        var portLink1: String? = "",
        var portLink2: String? = "",
        var portLink3: String? = "",
        var portLink4: String? = "",
        var uptimestring: String? = "",
        var zxAnEponOnuRxPower: String? = "",
        var zxAnEponOnuTxPower: String? = "",
        var id: String? = "",
        var objId: String? = "",
        var contract: String? = "",
        var pop: String? = "",
        var firstAccess: String? = "",
        var lastAccess: String? = "",
        var macAddress: String? = "",
        var ipFront: String? = "",
        var ipRoute: String? = "",
        var tRate: String? = "",
        var rRate: String? = "",
        var input: String? = "",
        var output: String? = "",
        var ip: String? = "",
        var ring: String? = "",
        var description: String? = "",
        var lastUpdate: String? = "",
        var vlanID: String? = ""
) : BaseModel()