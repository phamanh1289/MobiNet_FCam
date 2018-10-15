package vn.com.fpt.mobinet_fcam.ui.utilities.kill_session

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_kill_session.*
import okhttp3.ResponseBody
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.interfaces.ConfirmDialogInterface
import vn.com.fpt.mobinet_fcam.data.network.model.KillSessionModel
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
class KillSessionFragment : BaseFragment(), KillSessionContract.KillSessionView {
    @Inject
    lateinit var presenter: KillSessionPresenter
    private var searchContractModel: SearchContractModel? = null
    private val onClickToBack = object : ConfirmDialogInterface {
        override fun onClickOk() {
            activity?.onBackPressed()
        }

        override fun onClickCancel() {

        }
    }

    companion object {
        fun newInstance(model: SearchContractModel): KillSessionFragment {
            val args = Bundle()
            args.putParcelable(Constants.MODEL, model)
            val fragment = KillSessionFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kill_session, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {
        setTitle(TitleAndMenuModel(title = getString(R.string.kill_session_reset)))
        arguments?.let {
            searchContractModel = it.getParcelable(Constants.MODEL) ?: SearchContractModel()
        }
        showLoading()
        presenter.getKillSession(searchContractModel?.username.toString())
        fragKillSession_tvSubmit.setOnClickListener {
            initParamDeleteSession()
        }
    }

    private fun initParamDeleteSession() {
        AppUtils.showDialog(fragmentManager, title = getString(R.string.confirm), content = getString(R.string.want_to_delete_session), actionCancel = true, confirmDialogInterface = object : ConfirmDialogInterface {
            override fun onClickOk() {
                showLoading()
                presenter.toKillSession(searchContractModel?.objid.toString())
            }

            override fun onClickCancel() {

            }
        })
    }

    private fun loadDataKillSession(response: KillSessionModel) {
        response.run {
            if (date.isNotBlank()) {
                fragKillSession_tvDate.text = date
                fragKillSession_tvName.text = name
                fragKillSession_tvNasName.text = nasname
                fragKillSession_tvIpAddress.text = ipaddress
                fragKillSession_tvMacAddress.text = callerid
            } else AppUtils.showDialog(fragmentManager, title = getString(R.string.result), content = getString(R.string.not_found_contract), confirmDialogInterface = onClickToBack)
        }
        hideLoading()
    }

    override fun loadKillSession(response: KillSessionModel) {
        loadDataKillSession(response)
    }

    override fun loadToKillSession(response: ResponseBody) {
        AppUtils.showDialog(fragmentManager, title = getString(R.string.result), content = response.string(), confirmDialogInterface = onClickToBack)
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