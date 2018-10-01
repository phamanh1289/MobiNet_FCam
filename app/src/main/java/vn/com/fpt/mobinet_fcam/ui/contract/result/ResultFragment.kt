package vn.com.fpt.mobinet_fcam.ui.contract.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_result.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.SearchListContractModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
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

    companion object {
        fun newInstance(model: SearchListContractModel, typeContract: String): ResultFragment {
            val args = Bundle()
            args.putParcelable(Constants.MODEL, model)
            args.putString(Constants.TYPE_CONTRACT, typeContract)
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
        arguments?.let {
            resultModel = it.getParcelable(Constants.MODEL)
            typeContract = it.getString(Constants.TYPE_CONTRACT)
        }
        loadDataToView()
    }

    private fun loadDataToView() {
        fragResult_tvRemaining.text = resultModel.remain.toString()
        fragResult_tvVip.text = resultModel.vip.toString()
        fragResult_tvComingContract.text = resultModel.goingend.toString()
        fragResult_tvLateContract.text = resultModel.late.toString()
        if (typeContract == Constants.CONTRACT_MAINTENANCE){
            fragResult_tvHiOpenNet.text = Constants.NO_VALUE.toString()
            fragResult_llHiOpenNet.visibility = View.VISIBLE
        }
    }
}