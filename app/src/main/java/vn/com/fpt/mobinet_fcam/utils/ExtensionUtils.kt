package vn.com.fpt.mobinet_fcam.utils

import android.annotation.SuppressLint
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
inline fun <T> Boolean.then(param: () -> T): T? = if (this) param() else null

infix fun <T> Boolean.then(param: T): T? = if (this) param else null

//String
@SuppressLint("SimpleDateFormat")
infix fun String?.convertToDateFormat(value: String): String = if (this == null || this.isEmpty()) value else {
    SimpleDateFormat(Constants.TIME_DATE_FORMAT_FROM_SERVER).format(SimpleDateFormat(Constants.TIME_DATE_FORMAT).parse(this))
}

@SuppressLint("SimpleDateFormat")
infix fun String?.convertToFullFormat(value: String): String = if (this == null || this.isEmpty()) value else {
    if (this.contains("T")) {
        val result = this.replace("T", " ")
        SimpleDateFormat(Constants.TIME_DATE_FULL_FORMAT).format(SimpleDateFormat(if (this.contains(".")) Constants.TIME_DATE_FULL_FORMAT_FROM_SERVER_TYPE_1 else Constants.TIME_DATE_FULL_FORMAT_FROM_SERVER_TYPE_2).parse(result))
    } else this
}

@SuppressLint("SimpleDateFormat")
infix fun String?.isValidateDate(value: String): Boolean {
    val fromDate = SimpleDateFormat(Constants.TIME_DATE_FORMAT).parse(this)
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
