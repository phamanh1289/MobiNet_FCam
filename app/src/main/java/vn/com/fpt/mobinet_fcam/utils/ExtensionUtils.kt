package vn.com.fpt.mobinet_fcam.utils

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import java.text.SimpleDateFormat
import java.util.*

/**
 * *******************************************
 * * Created by AnhPT76 on 02/10/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */

//Boolean
//inline fun <T> Boolean.then(param: () -> T): T? = if (this) param() else null
//
//infix fun <T> Boolean.then(param: T): T? = if (this) param else null

//String
@SuppressLint("SimpleDateFormat")
infix fun TextView?.convertToDateFormat(value: String): String = if (this == null || this.text.toString().isEmpty()) value else {
    SimpleDateFormat(Constants.TIME_DATE_FORMAT_FROM_SERVER).format(SimpleDateFormat(Constants.TIME_DATE_FORMAT).parse(this.text.toString()))
}

@SuppressLint("SimpleDateFormat")
infix fun String?.convertToFullFormat(value: String): String = if (this == null || this.isEmpty()) value else {
    if (this.contains("T")) {
        val result = this.replace("T", " ")
        SimpleDateFormat(Constants.TIME_DATE_FULL_FORMAT).format(SimpleDateFormat(if (this.contains(".")) Constants.TIME_DATE_FULL_FORMAT_FROM_SERVER_TYPE_1 else Constants.TIME_DATE_FULL_FORMAT_FROM_SERVER_TYPE_2).parse(result))
    } else this
}

@SuppressLint("SimpleDateFormat")
infix fun String?.addTimeToDate(value: String): String = if (this == null || this.isNullOrEmpty()) value else {
    if (this.contains("T")) {
        val result = this.replace("T", " ")
        val date = SimpleDateFormat(if (this.contains(".")) Constants.TIME_DATE_FULL_FORMAT_FROM_SERVER_TYPE_1 else Constants.TIME_DATE_FULL_FORMAT_FROM_SERVER_TYPE_2).parse(result)
        val preFix = SimpleDateFormat(Constants.TIME_DATE_FULL_FORMAT_SHORT).format(date).split(" ")
        val calendar = Calendar.getInstance().apply {
            time = date
            add(Calendar.HOUR_OF_DAY, Constants.DELAY_HOUR)
        }
        "${SimpleDateFormat(Constants.TIME_DATE_FULL_FORMAT_SHORT).format(calendar.time)}-${preFix[Constants.SECOND_ITEM]}"
    } else this
}

@SuppressLint("SimpleDateFormat")
infix fun TextView?.isValidateDate(value: String): Boolean {
    val fromDate = SimpleDateFormat(Constants.TIME_DATE_FORMAT).parse(this?.text.toString())
    val toDate = SimpleDateFormat(Constants.TIME_DATE_FORMAT).parse(value)
    return fromDate.before(toDate)
}

@SuppressLint("SimpleDateFormat")
infix fun String?.getCurrentDate(value: Int): String {
    val c = Calendar.getInstance()
    if (value != 0)
        c.add(Calendar.DATE, value)
    return SimpleDateFormat(Constants.TIME_DATE_FORMAT).format(c.time)
}

infix fun TextView?.checkNoValue(value: String?): Boolean = this?.text.toString().toInt() == Constants.NO_VALUE

infix fun TextView?.onChange(view: View) {
    this?.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            view.visibility = if (s.toString().isNotBlank()) View.VISIBLE else View.GONE
        }
    })
}
