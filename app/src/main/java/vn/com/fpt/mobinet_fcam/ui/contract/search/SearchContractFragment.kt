package vn.com.fpt.mobinet_fcam.ui.contract.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
import vn.com.fpt.mobinet_fcam.utils.KeyboardUtils

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class SearchContractFragment : BaseFragment() {
//    @Inject
//    lateinit var presenter: BlankPresenter

    companion object {
        fun newInstance(supId: String): SearchContractFragment {
            val args = Bundle()
            args.putString("", supId)
            val fragment = SearchContractFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        getActivityComponent().inject(this)
//        presenter.onAttach(this)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {

    }

//    override fun loadLogin(response: ResponseModel) {
//
//    }
//
//    override fun handleError(response: String) {
//        hideLoading()
//        AppUtils.showDialog(fragmentManager, title = getString(R.string.mess_error_data), content = response, confirmDialogInterface = null)
//    }

//    override fun onDestroy() {
//        super.onDestroy()
//        presenter.onDetach()
//    }
}