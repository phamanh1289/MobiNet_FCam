package vn.com.fpt.mobinet_fcam.dagger.module

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import vn.com.fpt.mobinet_fcam.data.network.model.ErrorServerModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import java.io.IOException

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class ApiCustomInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code() >= 400) {
            throw IOException(ErrorServerModel.getErrorString(response))
        }
        val stringResponse: String = response.body()!!.string()
        try {
            return response.newBuilder()
                    .message(Constants.SUCCESSFUL)
                    .body(ResponseBody.create(response.body()!!.contentType(), stringResponse))
                    .build()
        } catch (e: Exception) {
            throw IOException(e.message)
        }
    }
}