package vn.com.fpt.mobinet_fcam.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import vn.com.fpt.mobinet_fcam.ui.main.MainActivity
import vn.com.fpt.mobinet_fcam.ui.splash_screen.SplashScreenActivity

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
object StartActivityUtils {

    fun toMainActivity(context: Context) {
        val intent = Intent().setClass(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
    }

    fun toSplashActivity(context: Context) {
        val intent = Intent().setClass(context, SplashScreenActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
    }

    fun toSettingPermission(context: Context, packageName: String) {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        intent.data = Uri.fromParts("package", packageName, null)
        context.startActivity(intent)
    }
}