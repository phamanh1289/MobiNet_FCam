package vn.com.fpt.mobinet_fcam.ui.base

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.tbruyelle.rxpermissions2.RxPermissions
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.dagger.component.ActivityComponent
import vn.com.fpt.mobinet_fcam.data.network.model.CustomTransaction
import vn.com.fpt.mobinet_fcam.data.network.model.InfoUserModel
import vn.com.fpt.mobinet_fcam.others.dialog.LoadingDialog
import vn.com.fpt.mobinet_fcam.ui.main.MainActivity
import vn.com.fpt.mobinet_fcam.utils.AppUtils
import vn.com.fpt.mobinet_fcam.utils.SharedPrefUtils

open class BaseActivity : AppCompatActivity(), BaseView {

    private lateinit var mActivityComponent: ActivityComponent
    private var sharePreferences: SharedPrefUtils? = null
    private var mDialogView: LoadingDialog? = null
    private var rxPermissions: RxPermissions? = null
    private var isCheckShowDialog = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityComponent = BaseApplication.instance.getApplicationComponent().getActivityComponent()
        this.let {
            sharePreferences = SharedPrefUtils(it)
            rxPermissions = RxPermissions(it)
        }
    }

    override fun getSharePreferences(): SharedPrefUtils {
        return sharePreferences ?: SharedPrefUtils(BaseApplication.instance)
    }

    override fun getCurrentFragment(): BaseFragment {
        val fragment = supportFragmentManager.findFragmentById(android.R.id.tabcontent)
        return fragment as? BaseFragment ?: BaseFragment()
    }

    fun getPermission(): RxPermissions? {
        return rxPermissions
    }

    override fun getActivityComponent(): ActivityComponent {
        return mActivityComponent
    }

    override fun showLoading() {
        this.let {
            if (isNetworkConnected()) {
                if (mDialogView == null)
                    mDialogView = LoadingDialog()
                if (!isCheckShowDialog) {
                    mDialogView?.show(supportFragmentManager, LoadingDialog::class.java.simpleName)
                    isCheckShowDialog = true
                }
            }
        }
    }

    override fun hideLoading() {
        this.let {
            mDialogView?.dismiss()
            isCheckShowDialog = false
        }
    }

    override fun isNetworkConnected(): Boolean {
        return AppUtils.getNetwork(this)
    }

    override fun addFragment(fragment: BaseFragment, isAddToBackStack: Boolean, isAnimation: Boolean) {
        addReplaceFragment(CustomTransaction(isAnimation = isAnimation), fragment, false, isAddToBackStack)
    }

    override fun replaceFragment(fragment: BaseFragment, isAddToBackStack: Boolean, isAnimation: Boolean) {
        addReplaceFragment(CustomTransaction(isAnimation = isAnimation), fragment, true, isAddToBackStack)
    }

    @SuppressLint("CommitTransaction")
    private fun addReplaceFragment(customTransaction: CustomTransaction, fragment: BaseFragment?, isReplace: Boolean, isAddToBackStack: Boolean) {

        fun getCurrentViewID(view: Int): Int {
            return when {
                view != 0 -> view
                this is MainActivity -> android.R.id.tabcontent
                else -> R.id.frmContainer
            }
        }

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.let { it ->
            if (customTransaction.isAnimation)
                it.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                        R.anim.enter_from_left, R.anim.exit_to_right)
            if (isReplace)
                it.replace(getCurrentViewID(customTransaction.containerViewId), fragment)
            else {
                val currentFragment = supportFragmentManager.findFragmentById(getCurrentViewID(customTransaction.containerViewId))
                currentFragment?.let { current -> it.hide(current) }
                it.add(getCurrentViewID(customTransaction.containerViewId), fragment, fragment!!::class.java.simpleName)
            }
            if (isAddToBackStack)
                it.addToBackStack(fragment!!::class.java.simpleName)
            it.commit()
        }
    }

    fun clearAllBackStack() {
        val fm = supportFragmentManager
        val count = fm?.backStackEntryCount
        count?.let {
            for (i in 0 until it) {
                onBackPressed()
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    fun setDefaultUser(user: InfoUserModel?) {
        sharePreferences?.infoUser = Gson().toJson(user,InfoUserModel::class.java)
        user?.let {
            BaseApplication.instance.setUser(it)
        }
    }

    fun getDefaultUser(): InfoUserModel? {
        sharePreferences?.infoUser.isNullOrBlank().let {
            val user = if (it) null else Gson().fromJson(sharePreferences?.infoUser, InfoUserModel::class.java)
            BaseApplication.instance.setUser(user)
        }
        return BaseApplication.instance.getUser()
    }
}