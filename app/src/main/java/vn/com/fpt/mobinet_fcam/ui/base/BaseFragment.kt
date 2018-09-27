package vn.com.fpt.mobinet_fcam.ui.base

import android.support.v4.app.Fragment
import vn.com.fpt.mobinet_fcam.dagger.component.ActivityComponent
import vn.com.fpt.mobinet_fcam.data.network.model.TitleAndMenuModel
import vn.com.fpt.mobinet_fcam.ui.main.MainActivity
import vn.com.fpt.mobinet_fcam.utils.SharedPrefUtils

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
open class BaseFragment : Fragment(), BaseView {

    protected var titleModel: TitleAndMenuModel = TitleAndMenuModel()

    override fun showLoading() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showLoading()
        }
    }

    override fun getCurrentFragment(): BaseFragment {
        return (activity as BaseActivity).getCurrentFragment()
    }

    override fun hideLoading() {
        if (activity is BaseActivity)
            (activity as BaseActivity).hideLoading()
    }

    override fun isNetworkConnected(): Boolean {
        return activity is BaseActivity && (activity as BaseActivity).isNetworkConnected()
    }

    override fun addFragment(fragment: BaseFragment, isAddToBackStack: Boolean, isAnimation: Boolean) {
        if (activity is BaseActivity) {
            if (activity is MainActivity) {
                val activity = activity as MainActivity
                if (isAddToBackStack) activity.mCountBack++
                activity.handleShowMenu()
            }
            (activity as BaseActivity).addFragment(fragment, isAddToBackStack, isAnimation)
        }
    }

    override fun replaceFragment(fragment: BaseFragment, isAddToBackStack: Boolean, isAnimation: Boolean) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).replaceFragment(fragment, isAddToBackStack, isAnimation)
        }
    }

    override fun getActivityComponent(): ActivityComponent {
        return (activity as BaseActivity).getActivityComponent()
    }

    override fun getSharePreferences(): SharedPrefUtils {
        return (activity as BaseActivity).getSharePreferences()
    }

    fun clearAllBackStack() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).clearAllBackStack()
        }
    }

    fun setTitle(model: TitleAndMenuModel) {
        if (activity is MainActivity) {
            titleModel = model
            (activity as MainActivity).setTitleMain(titleModel)
        }
    }

    fun getTitle(): TitleAndMenuModel {
        return titleModel
    }
}