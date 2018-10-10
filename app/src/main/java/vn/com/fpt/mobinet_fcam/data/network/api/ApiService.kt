package vn.com.fpt.mobinet_fcam.data.network.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import vn.com.fpt.mobinet_fcam.data.network.model.DetailContractModel
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
    //=====POST=====
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
    fun getDetailContractDepl(@Body map: HashMap<String, Any>): Observable<DetailContractModel>

    @POST("GetDetailContractMain")
    fun getDetailContractMain(@Body map: HashMap<String, Any>): Observable<DetailContractModel>

    @POST("UpdateDeploymentObjectV2")
    fun postUpdateContractDeployment(@Body map: HashMap<String, Any>): Observable<ResponseModel>

    @POST("GetListMemberOfTeam")
    fun getListMemberOfTeam(@Body map: HashMap<String, Any>): Observable<ResponseModel>

    @POST("DivisionStaffMain")
    fun postDivisionStaffMain(@Body map: HashMap<String, Any>): Observable<ResponseModel>

    @POST("DivisionMember")
    fun postDivisionMember(@Body map: HashMap<String, Any>): Observable<ResponseModel>

    //=====GET=====
    @GET("GetImage/{id}")
    fun getImage(@Path("id") id: String): Observable<ResponseBody>

    @GET("GetDeploymentObject/{userName}/{passWord}/{DeployID}/{ObjID}")
    fun getDetailContract(@Path("userName") userName: String, @Path("passWord") passWord: String, @Path("DeployID") deplId: Int, @Path("ObjID") objId: Int): Observable<ResponseBody>

    @GET("GetMaintenanceObject/{userName}/{passWord}/{MaintenanceID}/{ObjID}")
    fun getMaintenanceObject(@Path("userName") userName: String, @Path("passWord") passWord: String, @Path("MaintenanceID") mainId: Int, @Path("ObjID") objId: Int): Observable<ResponseBody>


}