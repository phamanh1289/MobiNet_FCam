package vn.com.fpt.mobinet_fcam.dagger.module

import okhttp3.Interceptor
import okhttp3.Response
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.ui.base.BaseApplication
import vn.com.fpt.mobinet_fcam.utils.AppUtils
import java.io.IOException

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class ConnectivityInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!AppUtils.getNetwork(BaseApplication.instance)) {
            throw IOException(Constants.ERROR_NETWORK)
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}