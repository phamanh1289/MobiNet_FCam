package vn.com.fpt.mobinet_fcam.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.ConnectivityManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentManager
import android.telephony.TelephonyManager
import vn.com.fpt.mobinet_fcam.data.interfaces.ConfirmDialogInterface
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.others.dialog.ConfirmDialog
import java.text.SimpleDateFormat
import java.util.*

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
object AppUtils {

    @SuppressLint("SimpleDateFormat")
    val formatter = SimpleDateFormat(Constants.TIME_DATE_FORMAT)

    fun getNetwork(context: Context?): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetwork = cm?.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun getDefaultImei(imei: String): Boolean {
        return when (imei) {
            Constants.DEFAULT_IMEI_1 -> true
            Constants.DEFAULT_IMEI_2 -> true
            Constants.DEFAULT_IMEI_3 -> true
            Constants.DEFAULT_IMEI_4 -> true
            Constants.DEFAULT_IMEI_5 -> true
            "868222039057725" -> true
            else -> false
        }
    }

    fun showDialog(fragmentManager: FragmentManager?, title: String = "", content: String = "", actionCancel: Boolean = false, confirmDialogInterface: ConfirmDialogInterface?) {
        fragmentManager?.let {
            val dialog = ConfirmDialog()
            dialog.setDataDialog(title = title, content = content, actionCancel = actionCancel, confirmDialogInterface = confirmDialogInterface)
            if (!it.isStateSaved)
                dialog.show(it, ConfirmDialog::class.java.simpleName)
        }
    }

    @SuppressLint("HardwareIds")
    fun getImeiDevice(context: Context?): String {
        var imeiDevice = ""
        context?.let {
            val manager = it.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (ActivityCompat.checkSelfPermission(it, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
                imeiDevice = manager.deviceId
        }
        return imeiDevice
    }

    @SuppressLint("HardwareIds")
    fun getImeiSim(context: Context?): String {
        var simImei = ""
        context?.let {
            val manager = it.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (ActivityCompat.checkSelfPermission(it, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
                simImei = manager.simSerialNumber
        }
        return simImei
    }

    fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            model
        } else {
            "$manufacturer $model"
        }
    }

    fun getCurrentDate(lateDate: Int): String {
        val c = Calendar.getInstance()
        if (lateDate != 0)
            c.add(Calendar.DATE, lateDate)
        return formatter.format(c.time)
    }
}