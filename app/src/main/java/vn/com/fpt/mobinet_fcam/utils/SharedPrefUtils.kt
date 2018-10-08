package vn.com.fpt.mobinet_fcam.utils

import android.content.Context
import android.content.SharedPreferences
import vn.com.fpt.mobinet_fcam.others.constant.Constants

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class SharedPrefUtils constructor(app: Context?) {

    companion object {
        private const val SHARED_PREF_NAME = "MobiNet_FCam"
        private const val INFO_USER_LOGIN = "info_user_login"
        private const val DAY_LOGIN = "day_login"
        private const val IMEI_DEVICE = "imeiDevice"
        private const val USER_NAME = "userName"
        private const val IP_ADDRESS = "ipAddress"
    }

    var ipAddress: String
        get() = sharedPreferences?.getString(IP_ADDRESS, "")!!
        set(value) = sharedPreferences?.put { putString(IP_ADDRESS, value) }!!
    var imeiDevice: String
        get() = sharedPreferences?.getString(IMEI_DEVICE, "")!!
        set(value) = sharedPreferences?.put { putString(IMEI_DEVICE, value) }!!
    var userName: String
        get() = sharedPreferences?.getString(USER_NAME, "")!!
        set(value) = sharedPreferences?.put { putString(USER_NAME, value) }!!
    var infoUser: String
        get() = sharedPreferences?.getString(INFO_USER_LOGIN, "")!!
        set(value) = sharedPreferences?.put { putString(INFO_USER_LOGIN, value) }!!
    var dayLogin: String
        get() = sharedPreferences?.getString(DAY_LOGIN, "")!!
        set(value) = sharedPreferences?.put { putString(DAY_LOGIN, value) }!!

    private inline fun SharedPreferences.put(body: SharedPreferences.Editor.() -> Unit) {
        val editor = this.edit()
        editor.body()
        editor.apply()
    }

    private val sharedPreferences by lazy(LazyThreadSafetyMode.NONE) {
        app?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    fun checkReLogin(): Boolean {
        return if (infoUser.isBlank())
            false
        else dayLogin == String().getCurrentDate(Constants.CURRENT_DATE)
    }

    fun toClearSessionLogin() {
        infoUser = ""
        dayLogin = ""
        imeiDevice = ""
    }

}