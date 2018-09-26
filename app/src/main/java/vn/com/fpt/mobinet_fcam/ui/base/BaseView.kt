package vn.com.fpt.mobinet_fcam.ui.base

import vn.com.fpt.mobinet_fcam.dagger.component.ActivityComponent
import vn.com.fpt.mobinet_fcam.utils.SharedPrefUtils

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface BaseView {
    fun showLoading()

    fun hideLoading()

    fun getActivityComponent(): ActivityComponent

    fun getSharePreferences(): SharedPrefUtils

    fun getCurrentFragment(): BaseFragment

    fun isNetworkConnected(): Boolean

    fun addFragment(fragment: BaseFragment, isAddToBackStack: Boolean, isAnimation: Boolean)

    fun replaceFragment(fragment: BaseFragment, isAddToBackStack: Boolean, isAnimation: Boolean)
}