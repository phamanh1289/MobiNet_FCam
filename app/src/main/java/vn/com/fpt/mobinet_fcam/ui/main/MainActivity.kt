package vn.com.fpt.mobinet_fcam.ui.main

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.interfaces.ConfirmDialogInterface
import vn.com.fpt.mobinet_fcam.data.network.model.TitleAndMenuModel
import vn.com.fpt.mobinet_fcam.ui.base.BaseActivity
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
import vn.com.fpt.mobinet_fcam.ui.contract.update.deployment.UpdateDeploymentFragment
import vn.com.fpt.mobinet_fcam.ui.contract.update.maintenance.UpdateMaintenanceFragment
import vn.com.fpt.mobinet_fcam.ui.functions.FunctionsFragment
import vn.com.fpt.mobinet_fcam.utils.AppUtils
import vn.com.fpt.mobinet_fcam.utils.StartActivityUtils
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityContract.MainView {


    @Inject
    lateinit var presenter: MainActivityPresenter
    var mCountBack = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        initView()
    }

    private fun initView() {
        actMain_ivMenuMain.setOnClickListener {
            if (mCountBack != 0) onBackPressed()
        }
        actMain_ivLogOut.setOnClickListener {
            if (it.visibility == View.VISIBLE) {
                AppUtils.showDialog(supportFragmentManager,title = getString(R.string.confirm), content = getString(R.string.mess_log_out_user), actionCancel = true, confirmDialogInterface = object : ConfirmDialogInterface {
                    override fun onClickOk() {
                        getSharePreferences().toClearSessionLogin()
                        StartActivityUtils.toSplashActivity(this@MainActivity)
                        this@MainActivity.finish()
                    }

                    override fun onClickCancel() {
                    }
                })
            }
        }
        addFragment(FunctionsFragment(), false, false)
        showLoading()
        presenter.getIpAddress()
    }

    fun setTitleMain(model: TitleAndMenuModel?) {
        model?.let {
            actMain_tvTitleMain.text = it.title
        }
    }

    fun handleShowMenu() {
        actMain_ivMenuMain.setImageResource(if (mCountBack == 0) R.drawable.ic_logo else R.drawable.ic_arrow_back)
        actMain_ivLogOut.visibility = if (mCountBack == 0) View.VISIBLE else View.GONE
    }

    private fun handleTitleMain() {
        val baseFragment = supportFragmentManager.findFragmentById(android.R.id.tabcontent)
        setTitleMain((baseFragment as? BaseFragment)?.getTitle())
    }

    override fun handleError(error: String) {
        AppUtils.showDialog(supportFragmentManager, title = getString(R.string.mess_error_data), content = error, confirmDialogInterface = null)
    }

    override fun onBackPressed() {
        when (mCountBack) {
            0 -> AppUtils.showDialog(supportFragmentManager,title = getString(R.string.confirm), content = getString(R.string.mess_close_app), actionCancel = true, confirmDialogInterface = object : ConfirmDialogInterface {
                override fun onClickOk() {
                    this@MainActivity.finish()
                }

                override fun onClickCancel() {

                }
            })
            else -> {
                val fragment = getCurrentFragment()
                when {
                    (fragment is UpdateDeploymentFragment && !fragment.exitUpdate) -> fragment.confirmExitUpdate()
                    (fragment is UpdateMaintenanceFragment && !fragment.exitUpdate) -> fragment.confirmExitUpdate()
                    else -> {
                        super.onBackPressed()
                        mCountBack--
                        handleTitleMain()
                        handleShowMenu()
                    }
                }

            }
        }
    }

    override fun loadIpAddress(data: String) {
        if (data.isNotBlank())
            getSharePreferences().ipAddress = data
        hideLoading()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
