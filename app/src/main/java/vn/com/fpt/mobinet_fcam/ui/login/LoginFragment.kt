package vn.com.fpt.mobinet_fcam.ui.login

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login.*
import vn.com.fpt.mobinet_fcam.BuildConfig
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.InfoUserModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
import vn.com.fpt.mobinet_fcam.utils.AppUtils
import vn.com.fpt.mobinet_fcam.utils.KeyboardUtils
import javax.inject.Inject

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class LoginFragment : BaseFragment(), LoginContract.LoginView {
    @Inject
    lateinit var presenter: LoginPresenter

    private var isCheckShowHidePassWord = true

    companion object {
        fun newInstance(supId: String): LoginFragment {
            val args = Bundle()
            args.putString("", supId)
            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {
        initOnClick()
        if (AppUtils.getDefaultImei(AppUtils.getImeiDevice(context))) {
            fragLogin_tvUser.setText(Constants.DEFAULT_USER)
            fragLogin_tvPass.setText(Constants.DEFAULT_PASSWORD)
        } else
            fragLogin_tvUser.setText(getSharePreferences().userName)
    }

    private fun validateData(): Boolean {
        val userName = fragLogin_tvUser.text.toString().trim()
        val result = when {
            userName.isBlank() -> getString(R.string.login_empty_user_name)
            fragLogin_tvPass.text.toString().trim().isBlank() -> getString(R.string.login_empty_password)
            else -> handleDefaultUser(userName)
        }
        if (result.isNotBlank())
            AppUtils.showDialog(fragmentManager, content = result, confirmDialogInterface = null)
        return !result.isNotBlank()
    }

    private fun handleDefaultUser(userName: String): String {
        val defaultUser = getSharePreferences().userName
        val user = (if (defaultUser.isNotBlank()) defaultUser else Constants.DEFAULT_USER)
        return if (userName != user && !AppUtils.getDefaultImei(AppUtils.getImeiDevice(context))) getString(R.string.mess_assigned_user, user) else ""
    }

    private fun handleShowHidePassword() {
        fragLogin_tvPass.transformationMethod =
                if (isCheckShowHidePassWord) HideReturnsTransformationMethod.getInstance()
                else PasswordTransformationMethod.getInstance()
        fragLogin_imgDoneError.isSelected = isCheckShowHidePassWord
        fragLogin_tvDoneError.isSelected = isCheckShowHidePassWord
        isCheckShowHidePassWord = !isCheckShowHidePassWord
    }

    private fun initOnClick() {
        fragLogin_tvSubmit.setOnClickListener {
            if (validateData()) {
                presenter.let { api ->
                    showLoading()
                    val map = HashMap<String, Any>()
                    map[Constants.PARAM_USER_NAME_LOW] = fragLogin_tvUser.text.toString()
                    map[Constants.PARAM_PASSWORD_LOW] = fragLogin_tvPass.text.toString()
                    map[Constants.PARAM_DEVICE_IMEI_LOW] = getSharePreferences().imeiDevice
                    map[Constants.PARAM_VERSION_APP_LOW] = BuildConfig.VERSION_CODE
//                    map[Constants.PARAM_VERSION_APP_LOW] = "2"
                    api.postLogin(map)
                }
            }
        }
        fragLogin_cbShowPass.setOnClickListener { handleShowHidePassword() }
    }

    private fun handleInfoUserData(data: InfoUserModel) {
        when (data.deptid) {
            Constants.REQUEST_UPDATE_APP -> {
                AppUtils.showDialog(fragmentManager, content = "Update", confirmDialogInterface = null)
            }
            Constants.LOGIN_FAILD -> {
                AppUtils.showDialog(fragmentManager, title = getString(R.string.mess_error_data), content = data.description, confirmDialogInterface = null)
            }
            else -> {
                AppUtils.showDialog(fragmentManager, title = getString(R.string.mess_success_data), content = "Success", confirmDialogInterface = null)
            }
        }
        hideLoading()
    }

    override fun loadLogin(response: InfoUserModel) {
        handleInfoUserData(response)
    }

    override fun handleError(response: String) {
        hideLoading()
        AppUtils.showDialog(fragmentManager, title = getString(R.string.mess_error_data), content = response, confirmDialogInterface = null)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}