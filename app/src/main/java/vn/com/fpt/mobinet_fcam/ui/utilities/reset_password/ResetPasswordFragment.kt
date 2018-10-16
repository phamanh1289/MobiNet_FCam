package vn.com.fpt.mobinet_fcam.ui.utilities.reset_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_reset_password.*
import kotlinx.android.synthetic.main.item_reset_mac_password.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.interfaces.ConfirmDialogInterface
import vn.com.fpt.mobinet_fcam.data.network.model.SearchContractModel
import vn.com.fpt.mobinet_fcam.data.network.model.TitleAndMenuModel
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
class ResetPasswordFragment : BaseFragment(), ResetPasswordContract.ResetPasswordView {
    @Inject
    lateinit var presenter: ResetPaswordPresenter
    private var searchContractModel: SearchContractModel? = null

    companion object {
        fun newInstance(model: SearchContractModel): ResetPasswordFragment {
            val args = Bundle()
            args.putParcelable(Constants.MODEL, model)
            val fragment = ResetPasswordFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reset_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {
        setTitle(TitleAndMenuModel(title = getString(R.string.utilities_password).replace("\n", " ")))
        arguments?.let {
            searchContractModel = it.getParcelable(Constants.MODEL) ?: SearchContractModel()
        }
        loadDataToView()
    }

    private fun loadDataToView() {
        searchContractModel?.run {
            fragReset_tvContract.text = contract
            fragReset_tvUserName.text = username
        }
        fragResetPassword_tvSubmit.setOnClickListener {
            val pass = fragResetPassword_etNewPassword.text.toString()
            if (pass.isNotBlank()) {
                searchContractModel?.run {
                    showLoading()
                    presenter.resetPassword(objid.toString(), pass, AppUtils.getIpLan(context))
                }
            } else AppUtils.showDialog(fragmentManager, title = getString(R.string.mess_error_data), content = getString(R.string.login_empty_password), confirmDialogInterface = null)
        }
    }

    override fun loadResetPassword(response: String) {
        AppUtils.showDialog(fragmentManager, title = getString(R.string.result), content = response, confirmDialogInterface = object : ConfirmDialogInterface {
            override fun onClickOk() {
                activity?.onBackPressed()
            }

            override fun onClickCancel() {

            }
        })
        hideLoading()
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