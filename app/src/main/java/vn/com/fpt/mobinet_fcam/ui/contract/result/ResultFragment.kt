package vn.com.fpt.mobinet_fcam.ui.contract.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_result.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.SearchListContractModel
import vn.com.fpt.mobinet_fcam.data.network.model.TitleAndMenuModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
import vn.com.fpt.mobinet_fcam.ui.contract.list_result.ListResultFragment
import vn.com.fpt.mobinet_fcam.utils.KeyboardUtils

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class ResultFragment : BaseFragment() {

    private lateinit var resultModel: SearchListContractModel
    private var typeContract = Constants.CONTRACT_DEPLOYMENT
    private var paramsJson = ""

    companion object {
        fun newInstance(model: SearchListContractModel, typeContract: String, params: String): ResultFragment {
            val args = Bundle()
            args.putParcelable(Constants.MODEL, model)
            args.putString(Constants.TYPE_CONTRACT, typeContract)
            args.putString(Constants.PARAMS_JSON, params)
            val fragment = ResultFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {
        setTitle(TitleAndMenuModel(title = getString(R.string.info_contract)))
        arguments?.let {
            resultModel = it.getParcelable(Constants.MODEL)
            typeContract = it.getString(Constants.TYPE_CONTRACT)
            paramsJson = it.getString(Constants.PARAMS_JSON)
        }
        loadDataToView()
        initOnClick()
    }

    private fun initOnClick() {
        fragResult_llRemaining.setOnClickListener { getListInfoContract(Constants.CONTRACT_REMAIN) }
        fragResult_llVip.setOnClickListener { getListInfoContract(Constants.CONTRACT_VIP) }
        fragResult_llComingContract.setOnClickListener { getListInfoContract(Constants.CONTRACT_COMING) }
        fragResult_llLateContract.setOnClickListener { getListInfoContract(Constants.CONTRACT_LATE) }
    }

    private fun getListInfoContract(type: Int) {
        addFragment(ListResultFragment.newInstance(paramsJson, typeContract, type), true, true)
    }

    private fun loadDataToView() {
        fragResult_tvRemaining.text = resultModel.remain.toString()
        fragResult_tvVip.text = resultModel.vip.toString()
        fragResult_tvComingContract.text = resultModel.goingend.toString()
        fragResult_tvLateContract.text = resultModel.late.toString()
        if (typeContract == Constants.CONTRACT_MAINTENANCE) {
            fragResult_tvHiOpenNet.text = Constants.NO_VALUE.toString()
            fragResult_llHiOpenNet.visibility = View.VISIBLE
        }
    }
}