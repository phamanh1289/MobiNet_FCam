package vn.com.fpt.mobinet_fcam.data.network.api

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import vn.com.fpt.mobinet_fcam.data.network.model.InfoUserModel
import vn.com.fpt.mobinet_fcam.data.network.model.ResponseModel
import vn.com.fpt.mobinet_fcam.data.network.model.SearchListContractModel

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface ApiService {

    @POST("CheckIMEI")
    fun checkIMEI(@Body map: HashMap<String, Any>): Observable<ResponseModel>

    @POST("LoginV2")
    fun postLogin(@Body map: HashMap<String, Any>): Observable<InfoUserModel>

    @POST("GetInfoContractDepl")
    fun getContractDeployment(@Body map: HashMap<String, Any>): Observable<SearchListContractModel>

    @POST("GetInfoContractMain")
    fun getContractMaintenance(@Body map: HashMap<String, Any>): Observable<SearchListContractModel>

    @POST("GetListInfoContractDepl")
    fun getListContractDepl(@Body map: HashMap<String, Any>): Observable<ResponseModel>

    @POST("GetListInfoContractMain")
    fun getListContractMain(@Body map: HashMap<String, Any>): Observable<ResponseModel>

    @POST("GetDetailContractDepl")
    fun getDetailContractDepl(@Body map: HashMap<String, Any>): Observable<ResponseModel>

    @POST("GetDetailContractMain")
    fun getDetailContractMain(@Body map: HashMap<String, Any>): Observable<SearchListContractModel>

}