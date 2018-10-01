package vn.com.fpt.mobinet_fcam.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.ConnectivityManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentManager
import android.telephony.TelephonyManager
import android.widget.TextView
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.interfaces.ConfirmDialogInterface
import vn.com.fpt.mobinet_fcam.data.network.model.SingleChoiceModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.others.dialog.ConfirmDialog
import vn.com.fpt.mobinet_fcam.others.dialog.singleChoice.SingChoiceDialog
import vn.com.fpt.mobinet_fcam.ui.contract.search_list.SearchListFragment
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

    fun showDialogSingChoice(fragmentManager: FragmentManager?, title: String, listData: ArrayList<SingleChoiceModel>, view: TextView, itemSelected: Int) {
        val dialog = SingChoiceDialog()
        dialog.setDataDialog(title = title, list = listData, index = itemSelected) { position ->
            listData[itemSelected].status = false
            listData[position].status = true
            val fragment = fragmentManager?.findFragmentById(android.R.id.tabcontent)
            when (fragment) {
                is SearchListFragment ->
                    fragment.setDefaultValueIndex(view.id, position)
            }
            view.text = listData[position].account
            dialog.submitData(listData)
            dialog.dismiss()
        }
        dialog.show(fragmentManager, SingChoiceDialog::class.java.simpleName)
    }

    fun showPickTime(context: Context?, tvDate: TextView, typeDate: Boolean) {
        val calendar = Calendar.getInstance()
        context?.let {
            val datePickerDialog = DatePickerDialog(it, { _, year, monthOfYear, dayOfMonth ->
                tvDate.text = it.resources.getString(R.string.date_time_format, toConvertMonth(dayOfMonth), toConvertMonth(monthOfYear + 1), year.toString())
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
            val arrDate = tvDate.text.toString()
            if (arrDate.isNotBlank()) {
                val list = if (arrDate.contains("-")) arrDate.split("-") else arrDate.split("/")
                datePickerDialog.updateDate(list[2].toInt(), list[1].toInt() - 1, list[0].toInt())
            }
            if (typeDate)
                datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 1000
            else
                datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
            datePickerDialog.show()
        }
    }

    private fun toConvertMonth(month: Int): String {
        return if (month < 10) "0$month" else month.toString()
    }

    fun getDefaultDateSearch(toDate: TextView, fromDate: TextView, lateDate: Int) {
        toDate.text = getCurrentDate(Constants.CURRENT_DATE)
        fromDate.text = getCurrentDate(lateDate)
    }

    fun handleCheckDate(context: Context?, start: String, end: String): String {
        context?.let {
            val startDate = formatter.parse(start)
            val endDate = formatter.parse(end)
            if (endDate.before(startDate))
                return it.getString(R.string.error_date)
        }
        return ""
    }

    fun toConvertDateFormat(context: Context?, date: String): String {
        val arr: List<String> = if (date.contains("/")) date.split("/") else date.split("-")
        context?.let {
            return it.getString(R.string.date_time_format, arr[2], arr[1], arr[0])
        }
        return ""
    }

    fun toConvertTimeToString(context: Context?, time: String): String {
        return when {
            time.isNullOrBlank() -> "N/A"
            (time.contains("T")) -> {
                val arr = time.split("T")
                "${toConvertDateFormat(context, arr[0])} ${arr[1].split(".")[0]}"
            }
            else -> time
        }
    }
}