package vn.com.fpt.mobinet_fcam.utils

import android.content.Context
import android.content.SharedPreferences

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
    }

//    var imeiDevice: String
//        get() = sharedPreferences?.getString(IMEI_DEVICE, "")!!
//        set(value) = sharedPreferences?.put { putString(IMEI_DEVICE, value) }!!

    private inline fun SharedPreferences.put(body: SharedPreferences.Editor.() -> Unit) {
        val editor = this.edit()
        editor.body()
        editor.apply()
    }

    private val sharedPreferences by lazy(LazyThreadSafetyMode.NONE) {
        app?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

//    fun checkReLogin(): Boolean {
//        return if (accountName.isBlank())
//            false
//        else createDate == AppUtils.getCurrentDate(Constants.CURRENT_DATE)
//    }

//    fun toClearSessionLogin() {
//        accountName = ""
//        createDate = ""
//    }

}