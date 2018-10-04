package vn.com.fpt.mobinet_fcam.ui.contract.update

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_update_contract.*
import kotlinx.android.synthetic.main.item_cable.*
import kotlinx.android.synthetic.main.item_cable_info.*
import kotlinx.android.synthetic.main.item_modem_stb.*
import kotlinx.android.synthetic.main.item_reason_result.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.*
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.others.datacore.DataCore
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
import vn.com.fpt.mobinet_fcam.ui.contract.update.adapter.UpdateContractAdapter
import vn.com.fpt.mobinet_fcam.utils.AppUtils
import vn.com.fpt.mobinet_fcam.utils.KeyboardUtils
import vn.com.fpt.mobinet_fcam.utils.onChange
import javax.inject.Inject

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class UpdateContractFragment : BaseFragment(), UpdateContractContract.UpdateContractView {
    @Inject
    lateinit var presenter: UpdateContractPresenter

    private lateinit var infoContract: DetailContractModel
    private var listInDoor = ArrayList<SingleChoiceModel>()
    private var listOutDoor = ArrayList<SingleChoiceModel>()
    private var listOtherCable = ArrayList<SingleChoiceModel>()
    private var listRouter = ArrayList<SingleChoiceModel>()
    private var listModem = ArrayList<SingleChoiceModel>()
    private var listTypeModem = ArrayList<SingleChoiceModel>()
    private var listStb = ArrayList<SingleChoiceModel>()
    private var listTypeStb = ArrayList<SingleChoiceModel>()
    private var listToHour = ArrayList<SingleChoiceModel>()
    private var listFromHour = ArrayList<SingleChoiceModel>()
    private var listReasonDelay = ArrayList<SingleChoiceModel>()
    private var listResult = ArrayList<SingleChoiceModel>()
    private var listContractKeyValue = ArrayList<DetailContractKeyValueModel>()
    private var adapterCableInfo = UpdateContractAdapter()
    private var indexModem = 0
    private var indexTypeModem = 0
    private var indexStb = 0
    private var indexTypeStb = 0
    private var indexInDoor = 0
    private var indexOutDoor = 0
    private var indexOtherCable = 0
    private var indexRouter = 0
    private var indexReasonDelay = 0
    private var indexToHour = 0
    private var indexResult = 0
    private var indexFromHour = 0
    private var serviceType = 0
    private var stepUpdate = Constants.STEP_UPDATE_CABLE

    companion object {
        fun newInstance(item: DetailContractModel, serviceType: Int): UpdateContractFragment {
            val args = Bundle()
            args.putParcelable(Constants.MODEL, item)
            args.putInt(Constants.PARAM_SERVICE_TYPE, serviceType)
            val fragment = UpdateContractFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_update_contract, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {
        setTitle(TitleAndMenuModel(title = getString(R.string.update_contract)))

        arguments?.let {
            infoContract = it.getParcelable(Constants.MODEL)
            serviceType = it.getInt(Constants.PARAM_SERVICE_TYPE)
        }
        getDefaultDataList()
        initSingleDialog()
        initOnClick()
        initCableInfoView()
    }

    private fun initOnClick() {
        fragUpdateContract_tvNext.setOnClickListener {
            if (stepUpdate < Constants.STEP_UPDATE_REASON_RESULT)
                handleStepUpdate(Constants.NEXT_STEP_UPDATE)
        }
        fragUpdateContract_tvBack.setOnClickListener {
            if (stepUpdate > Constants.STEP_UPDATE_CABLE)
                handleStepUpdate(Constants.BACK_STEP_UPDATE)
        }
        fragUpdateContract_tvTo.setOnClickListener { AppUtils.showPickTime(context, fragUpdateContract_tvTo, Constants.SET_CURRENT_IS_MIN_DATE) }
        fragUpdateContract_tvFrom.setOnClickListener { AppUtils.showPickTime(context, fragUpdateContract_tvFrom, Constants.SET_CURRENT_IS_MIN_DATE) }
        fragUpdateContract_imgDeleteTo.setOnClickListener { fragUpdateContract_tvTo.text = "" }
        fragUpdateContract_imgDeleteFrom.setOnClickListener { fragUpdateContract_tvFrom.text = "" }
        fragUpdateContract_tvTo.onChange(fragUpdateContract_imgDeleteTo)
        fragUpdateContract_tvFrom.onChange(fragUpdateContract_imgDeleteFrom)
    }

    private fun handleStepUpdate(step: Int) {
        hideAllView()
        stepUpdate += step
        when (stepUpdate) {
            Constants.STEP_UPDATE_CABLE -> {
                fragUpdateContract_llCable.visibility = View.VISIBLE
            }
            Constants.STEP_UPDATE_CABLE_INFO -> {
                fragUpdateContract_llCableInfo.visibility = View.VISIBLE
            }
            Constants.STEP_UPDATE_MODEM_STB -> {
                fragUpdateContract_llModemStb.visibility = View.VISIBLE
            }
            Constants.STEP_UPDATE_REASON_RESULT -> {
                fragUpdateContract_llReasonResult.visibility = View.VISIBLE
            }
        }
        fragUpdateContract_tvBack.visibility = if (stepUpdate == Constants.STEP_UPDATE_CABLE) View.GONE else View.VISIBLE
        fragUpdateContract_tvNext.text = getString(if (stepUpdate == Constants.STEP_UPDATE_REASON_RESULT) R.string.update else R.string.next)
    }

    private fun initCableInfoView() {
        listContractKeyValue = DataCore.getListCableInfo(context)
        adapterCableInfo.submitList(listContractKeyValue)
        fragUpdateContract_rvMain.apply {
            val layout = LinearLayoutManager(context)
            layoutManager = layout
            setHasFixedSize(true)
            adapter = adapterCableInfo
        }
        adapterCableInfo.notifyDataSetChanged()
    }

    private fun hideAllView() {
        fragUpdateContract_llCable.visibility = View.GONE
        fragUpdateContract_llCableInfo.visibility = View.GONE
        fragUpdateContract_llModemStb.visibility = View.GONE
        fragUpdateContract_llReasonResult.visibility = View.GONE
    }

    private fun initSingleDialog() {
        //thêm dữ liêu Cable
        fragUpdateContract_tvInDoor.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager = fragmentManager, title = getString(R.string.update_indoor), view = fragUpdateContract_tvInDoor, listData = listInDoor, itemSelected = indexInDoor) }
        fragUpdateContract_tvOutDoor.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager = fragmentManager, title = getString(R.string.update_outdoor), view = fragUpdateContract_tvOutDoor, listData = listOutDoor, itemSelected = indexOutDoor) }
        fragUpdateContract_tvOtherCable.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager = fragmentManager, title = getString(R.string.type_cable_other_cable), view = fragUpdateContract_tvOtherCable, listData = listOtherCable, itemSelected = indexOtherCable) }
        fragUpdateContract_tvRouter.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager = fragmentManager, title = getString(R.string.update_router_amount), view = fragUpdateContract_tvRouter, listData = listRouter, itemSelected = indexRouter) }
        //thêm dữ liệu Modem & Stb
        fragUpdateContract_tvModem.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager = fragmentManager, title = getString(R.string.update_modem), view = fragUpdateContract_tvModem, listData = listModem, itemSelected = indexModem) }
        fragUpdateContract_tvTypeModem.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager = fragmentManager, title = getString(R.string.update_type), view = fragUpdateContract_tvTypeModem, listData = listTypeModem, itemSelected = indexTypeModem) }
        fragUpdateContract_tvStb.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager = fragmentManager, title = getString(R.string.update_stb), view = fragUpdateContract_tvStb, listData = listStb, itemSelected = indexStb) }
        fragUpdateContract_tvTypeStb.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager = fragmentManager, title = getString(R.string.update_type), view = fragUpdateContract_tvTypeStb, listData = listTypeStb, itemSelected = indexTypeStb) }
        //thêm dữ liệu other
        fragUpdateContract_tvToHour.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager = fragmentManager, title = getString(R.string.update_to_hour), view = fragUpdateContract_tvToHour, listData = listToHour, itemSelected = indexToHour) }
        fragUpdateContract_tvFromHour.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager = fragmentManager, title = getString(R.string.update_from_hour), view = fragUpdateContract_tvFromHour, listData = listFromHour, itemSelected = indexFromHour) }
        fragUpdateContract_tvReasonDelay.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager = fragmentManager, title = getString(R.string.update_reason_delay), view = fragUpdateContract_tvReasonDelay, listData = listReasonDelay, itemSelected = indexReasonDelay) }
        fragUpdateContract_tvResult.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager = fragmentManager, title = getString(R.string.update_result), view = fragUpdateContract_tvResult, listData = listResult, itemSelected = indexResult) }
    }

    fun setIndexSelected(view: View, position: Int) {
        when (view.id) {
            R.id.fragUpdateContract_tvInDoor -> indexInDoor = position
            R.id.fragUpdateContract_tvOutDoor -> indexOutDoor = position
            R.id.fragUpdateContract_tvOtherCable -> indexOtherCable = position
            R.id.fragUpdateContract_tvRouter -> indexRouter = position
            R.id.fragUpdateContract_tvModem -> indexModem = position
            R.id.fragUpdateContract_tvTypeModem -> indexTypeModem = position
            R.id.fragUpdateContract_tvStb -> indexStb = position
            R.id.fragUpdateContract_tvTypeStb -> indexTypeStb = position
            R.id.fragUpdateContract_tvToHour -> indexToHour = position
            R.id.fragUpdateContract_tvFromHour -> indexFromHour = position
            R.id.fragUpdateContract_tvReasonDelay -> indexReasonDelay = position
            R.id.fragUpdateContract_tvResult -> indexResult = position
        }
    }

    private fun getDefaultDataList() {
        listInDoor = DataCore.getListCable(context)
        setDefaultFirstItem(listInDoor, fragUpdateContract_tvInDoor)
        listOutDoor = DataCore.getListCable(context)
        setDefaultFirstItem(listOutDoor, fragUpdateContract_tvOutDoor)
        listOtherCable = DataCore.getListOtherCable(context)
        setDefaultFirstItem(listOtherCable, fragUpdateContract_tvOtherCable)
        listRouter = DataCore.getListRouter(context)
        setDefaultFirstItem(listRouter, fragUpdateContract_tvRouter)
        listModem = DataCore.getListModem(context)
        setDefaultFirstItem(listModem, fragUpdateContract_tvModem)
        listTypeModem = DataCore.getListTypeModem(context)
        setDefaultFirstItem(listTypeModem, fragUpdateContract_tvTypeModem)
        listStb = DataCore.getListStb(context)
        setDefaultFirstItem(listStb, fragUpdateContract_tvStb)
        listTypeStb = DataCore.getListTypeStb(context)
        setDefaultFirstItem(listTypeStb, fragUpdateContract_tvTypeStb)
        listToHour = DataCore.getListHour()
        setDefaultFirstItem(listToHour, fragUpdateContract_tvToHour)
        listFromHour = DataCore.getListHour()
        setDefaultFirstItem(listFromHour, fragUpdateContract_tvFromHour)
        listReasonDelay = DataCore.getListReason(context)
        setDefaultFirstItem(listReasonDelay, fragUpdateContract_tvReasonDelay)
        listResult = DataCore.getListResult(context)
        setDefaultFirstItem(listResult, fragUpdateContract_tvResult)
    }

    private fun setDefaultFirstItem(list: ArrayList<SingleChoiceModel>, textView: TextView) {
        list[Constants.FIRST_ITEM].status = true
        textView.text = list[Constants.FIRST_ITEM].account
    }

    override fun loadUpdateContract(response: ResponseModel) {
//        http@ //wsfcam.fpt.vn/FCAM.svc/GetDeploymentObject/SIR3-Pitou.Pich/306017/2169492/1157182
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