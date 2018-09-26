package vn.com.fpt.mobinet_fcam.data.network.model

import android.support.annotation.IdRes

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
data class CustomTransaction(@IdRes var containerViewId: Int = 0, var isAnimation: Boolean = false)