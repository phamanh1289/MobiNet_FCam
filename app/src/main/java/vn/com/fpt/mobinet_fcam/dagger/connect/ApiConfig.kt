package vn.com.fpt.mobinet_fcam.dagger.connect

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
object ApiConfig {
    fun createConnectionDetail(typeApi: ApiConfigType?): ApiConfigDetail {
        val type = typeApi ?: ApiConfigType.DEVELOP
        var url = ""
        when (type) {
            ApiConfigType.DEVELOP -> {
                url = "http://wsfcam.fpt.vn/FCAM.svc/"
            }
            else -> {
            }
        }
        return ApiConfigDetail(baseURL = url)
    }
}