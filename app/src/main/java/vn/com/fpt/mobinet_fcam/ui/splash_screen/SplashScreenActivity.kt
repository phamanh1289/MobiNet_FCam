package vn.com.fpt.mobinet_fcam.ui.splash_screen

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.interfaces.ConfirmDialogInterface
import vn.com.fpt.mobinet_fcam.data.network.model.ResponseModel
import vn.com.fpt.mobinet_fcam.data.network.model.UserLoginModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.ui.base.BaseActivity
import vn.com.fpt.mobinet_fcam.ui.login.LoginFragment
import vn.com.fpt.mobinet_fcam.utils.AppUtils
import vn.com.fpt.mobinet_fcam.utils.StartActivityUtils
import javax.inject.Inject

class SplashScreenActivity : BaseActivity(), SplashScreenActivityContract.SplashScreenView {

    @Inject
    lateinit var presenter: SplashScreenActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        initView()
    }

    private fun initView() {
        Handler().postDelayed({
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                try {
                    getPermission()?.requestEachCombined(
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.WAKE_LOCK)
                            ?.subscribe {
                                if (it.granted) {
                                    initParamCheckImei()
                                } else
                                    AppUtils.showDialog(fragmentManager = supportFragmentManager, content = getString(R.string.mess_miss_permission), actionCancel = true, confirmDialogInterface = object : ConfirmDialogInterface {
                                        override fun onClickCancel() {
                                            finish()
                                        }

                                        override fun onClickOk() {
                                            StartActivityUtils.toSettingPermission(this@SplashScreenActivity, packageName)
                                            finish()
                                        }
                                    })
                            }
                } catch (e: Exception) {
                    startActivity(Intent(this, SplashScreenActivity::class.java))
                    finish()
                }
            }
        }, 1500)
    }

    private fun initParamCheckImei() {
        if (!AppUtils.getDefaultImei(AppUtils.getImeiDevice(this)))
            presenter.let {
                showLoading()
                val map = HashMap<String, Any>()
                map[Constants.PARAM_SIM_IMEI] = AppUtils.getImeiSim(this)
                map[Constants.PARAM_DEVICE_IMEI] = AppUtils.getImeiDevice(this)
                map[Constants.PARAM_DEVICE_MODEL] = AppUtils.getDeviceName()
                map[Constants.PARAM_OS_VERSION] = Build.VERSION.RELEASE
                it.checkImei(map)
            }
        else {
            getSharePreferences().imeiDevice = AppUtils.getImeiDevice(this)
            checkReLogin()
        }
    }

    private fun checkReLogin(){
        if (getSharePreferences().checkReLogin())
            StartActivityUtils.toMainActivity(this)
        else{
            getSharePreferences().toClearSessionLogin()
            addFragment(LoginFragment(), false, true)
        }
    }

    private fun handleDataCheckImei(data: Any) {
        val listUserLogin: ArrayList<UserLoginModel> = Gson().fromJson(data.toString(), object : TypeToken<ArrayList<UserLoginModel>>() {}.type)
        listUserLogin.let {
            it.map { item ->
                when (item.isActive) {
                    false -> {
                        AppUtils.showDialog(supportFragmentManager, content = getString(R.string.mess_error_not_active), confirmDialogInterface = object : ConfirmDialogInterface {
                            override fun onClickOk() {
                                this@SplashScreenActivity.finish()
                            }

                            override fun onClickCancel() {
                            }
                        })
                        hideLoading()
                    }
                    else -> handleDataUser(item)
                }
            }
        }
    }

    private fun handleDataUser(userLogin: UserLoginModel) {
        if (userLogin.userID.isNotBlank())
            getSharePreferences().userName = userLogin.userID
        if (userLogin.deviceIMEI.isNotBlank())
            getSharePreferences().imeiDevice = userLogin.deviceIMEI
        hideLoading()
        checkReLogin()
    }

    override fun loadCheckImei(response: ResponseModel) {
        if (response.Code == Constants.REQUEST_SUCCESS)
            handleDataCheckImei(response.Data)
        else {
            hideLoading()
            AppUtils.showDialog(supportFragmentManager, content = response.Description, confirmDialogInterface = null)
        }
    }

    override fun handleError(error: String) {
        hideLoading()
        AppUtils.showDialog(supportFragmentManager, title = getString(R.string.mess_error_data), content = error, confirmDialogInterface = null)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
