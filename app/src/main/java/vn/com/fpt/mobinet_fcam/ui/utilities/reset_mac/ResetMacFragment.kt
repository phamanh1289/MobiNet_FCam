package vn.com.fpt.mobinet_fcam.ui.utilities.reset_mac

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_reset_mac.*
import kotlinx.android.synthetic.main.item_reset_mac_password.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.interfaces.ConfirmDialogInterface
import vn.com.fpt.mobinet_fcam.data.network.model.MacModel
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
class ResetMacFragment : BaseFragment(), ResetMacContract.ResetMacView {
    @Inject
    lateinit var presenter: ResetMacPresenter
    private var searchContractModel: SearchContractModel? = null

    companion object {
        fun newInstance(model: SearchContractModel): ResetMacFragment {
            val args = Bundle()
            args.putParcelable(Constants.MODEL, model)
            val fragment = ResetMacFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reset_mac, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {
        setTitle(TitleAndMenuModel(title = getString(R.string.utilities_mac).replace("\n", " ")))
        arguments?.let {
            searchContractModel = it.getParcelable(Constants.MODEL) ?: SearchContractModel()
        }
        initParamGetMac()
        fragResetMac_tvSubmit.setOnClickListener {
            val result = fragResetMac_etNewMacAddress.text.toString().trim().isNotBlank()
            AppUtils.showDialog(fragmentManager,
                    title = getString(if (result) R.string.confirm else R.string.mess_error_data),
                    content = if (result) getString(R.string.mess_update_new_mac_address, fragResetMac_etNewMacAddress.text.toString()) else getString(R.string.error_mac_address),
                    actionCancel = result,
                    confirmDialogInterface = if (result) object : ConfirmDialogInterface {
                        override fun onClickOk() {
                            searchContractModel?.run {
                                showLoading()
                                presenter.resetMac(objid.toString(), fragResetMac_etNewMacAddress.text.toString())
                            }
                        }

                        override fun onClickCancel() {

                        }
                    } else null)
        }
    }

    private fun initParamGetMac() {
        showLoading()
        presenter.getMac(searchContractModel?.objid.toString())
    }

    private fun loadDataToView(response: MacModel) {
        searchContractModel?.run {
            fragReset_tvContract.text = contract
            fragReset_tvUserName.text = username
            fragResetMac_tvFullName.text = fullname
            fragResetMac_tvStatus.text = status
        }
        response.run {
            fragResetMac_tvIpAddress.text = ip
            fragResetMac_tvMacAddress.text = mac
        }
        hideLoading()
    }

    override fun loadMac(response: MacModel) {
        loadDataToView(response)
    }

    override fun loadResetMac(response: String) {
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