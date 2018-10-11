package vn.com.fpt.mobinet_fcam.data.network.api

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import vn.com.fpt.mobinet_fcam.data.network.model.HiOpenNetModel

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface ApiHiOpennetService {
    //=====POST=====
    @POST("isc-check-contract-use-app")
    fun checkContractHiOpennet(@Body map: HashMap<String, Any>): Observable<HiOpenNetModel>

}