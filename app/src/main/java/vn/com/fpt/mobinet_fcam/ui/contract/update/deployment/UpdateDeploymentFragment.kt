package vn.com.fpt.mobinet_fcam.ui.contract.update.deployment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_update_contract_deployment.*
import kotlinx.android.synthetic.main.item_cable.*
import kotlinx.android.synthetic.main.item_cable_info.*
import kotlinx.android.synthetic.main.item_modem_stb.*
import kotlinx.android.synthetic.main.item_reason_result.*
import okhttp3.ResponseBody
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.interfaces.ConfirmDialogInterface
import vn.com.fpt.mobinet_fcam.data.network.model.DetailContractModel
import vn.com.fpt.mobinet_fcam.data.network.model.ResponseModel
import vn.com.fpt.mobinet_fcam.data.network.model.TitleAndMenuModel
import vn.com.fpt.mobinet_fcam.data.network.model.UpdateContractModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
import vn.com.fpt.mobinet_fcam.utils.*
import javax.inject.Inject

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class UpdateDeploymentFragment : BaseFragment(), UpdateDeploymentContract.UpdateContractView {
    @Inject
    lateinit var presenter: UpdateDeploymentPresenter

    private lateinit var dataCore: DataCoreUpdateContract
    var exitUpdate = false

    companion object {
        fun newInstance(item: DetailContractModel, serviceType: Int): UpdateDeploymentFragment {
            val args = Bundle()
            args.putParcelable(Constants.MODEL, item)
            args.putInt(Constants.PARAM_SERVICE_TYPE, serviceType)
            val fragment = UpdateDeploymentFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_update_contract_deployment, container, false)
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
        dataCore = DataCoreUpdateContract(context)
        dataCore.getDefaultDataList()
        arguments?.let {
            dataCore.infoContract = it.getParcelable(Constants.MODEL)
            dataCore.serviceType = it.getInt(Constants.PARAM_SERVICE_TYPE)
        }
        handleStepUpdate(Constants.STEP_1_UPDATE_CABLE)
        getDefaultDataList()
        initSingleDialog()
        initOnClick()
        initParamGetDetailUpdate()
    }

    private fun getDefaultDataList() {
        dataCore.run {
            setDefaultFirstItem(listInDoor, fragUpdateContract_tvInDoor)
            setDefaultFirstItem(listOutDoor, fragUpdateContract_tvOutDoor)
            setDefaultFirstItem(listOtherCable, fragUpdateContract_tvOtherCable)
            setDefaultFirstItem(listRouter, fragUpdateContract_tvRouter)
            setDefaultFirstItem(listModem, fragUpdateContract_tvModem)
            setDefaultFirstItem(listTypeModem, fragUpdateContract_tvTypeModem)
            setDefaultFirstItem(listStb, fragUpdateContract_tvStb)
            setDefaultFirstItem(listTypeStb, fragUpdateContract_tvTypeStb)
            setDefaultFirstItem(listToHour, fragUpdateContract_tvToHour)
            setDefaultFirstItem(listFromHour, fragUpdateContract_tvFromHour)
            setDefaultFirstItem(listReasonDelay, fragUpdateContract_tvReasonDelay)
            setDefaultFirstItem(listResult, fragUpdateContract_tvResult)
        }
    }

    fun confirmExitUpdate() {
        AppUtils.showDialog(fragmentManager, content = getString(R.string.mess_exit_update_contract), actionCancel = true, confirmDialogInterface = object : ConfirmDialogInterface {
            override fun onClickOk() {
                exitUpdate = true
                activity?.onBackPressed()
            }

            override fun onClickCancel() {
                exitUpdate = false
            }
        })
    }

    private fun initParamGetDetailUpdate() {
        getDefaultUser()?.let {
            showLoading()
            dataCore.infoContract.let { item ->
                presenter.getDetailUpdate(it.mobiaccount, it.password, item.deployid, item.objid)
            }
        }
    }

    private fun initOnClick() {
        fragUpdateContract_tvNext.setOnClickListener {
            if (dataCore.stepUpdate < Constants.STEP_4_UPDATE_REASON_RESULT)
                handleStepUpdate(Constants.NEXT_STEP_UPDATE)
            else AppUtils.showDialog(fragmentManager, content = getString(R.string.mess_update_contract), actionCancel = true, confirmDialogInterface = object : ConfirmDialogInterface {
                override fun onClickOk() {
                    presenter.let { pre ->
                        showLoading()
                        val map = HashMap<String, Any>()
                        addPramsToUpdate(map)
                        pre.postUpdateContractDeployment(map)
                    }
                }

                override fun onClickCancel() {

                }
            })
        }
        fragUpdateContract_tvBack.setOnClickListener {
            if (dataCore.stepUpdate > Constants.STEP_1_UPDATE_CABLE)
                handleStepUpdate(Constants.BACK_STEP_UPDATE)
        }
        fragUpdateContract_tvTo.setOnClickListener { AppUtils.showPickTime(context, fragUpdateContract_tvTo, Constants.SET_CURRENT_IS_MIN_DATE) }
        fragUpdateContract_tvFrom.setOnClickListener { AppUtils.showPickTime(context, fragUpdateContract_tvFrom, Constants.SET_CURRENT_IS_MIN_DATE) }
        fragUpdateContract_imgDeleteTo.setOnClickListener { fragUpdateContract_tvTo.text = "" }
        fragUpdateContract_imgDeleteFrom.setOnClickListener { fragUpdateContract_tvFrom.text = "" }
        fragUpdateContract_tvTo.onChange(fragUpdateContract_imgDeleteTo)
        fragUpdateContract_tvFrom.onChange(fragUpdateContract_imgDeleteFrom)
    }

    private fun addPramsToUpdate(map: HashMap<String, Any>) {
        getDefaultUser()?.let {
            map[Constants.PARAM_USER_NAME_UPPER] = it.mobiaccount
            map[Constants.PARAM_PASSWORD_UPPER] = it.password
        }
        dataCore.run {
            addParams(map)
            map[Constants.PARAM_OBJ_ID] = updateContractModel.objid
            map[Constants.PARAM_SERVICE_TYPE] = serviceType
            map[Constants.PARAM_SUP_INF_ID] = updateContractModel.deployid
            map[Constants.PARAM_CREATE_BY] = infoContract.createby
            map[Constants.PARAM_OUTDOOR] = fragUpdateContract_etOutDoor.text.toString()
            map[Constants.PARAM_OUTDOOR_TYPE] = listOutDoor[indexOutDoor].id
            map[Constants.PARAM_INDOOR] = fragUpdateContract_etInDoor.text.toString()
            map[Constants.PARAM_INDOOR_TYPE] = listInDoor[indexInDoor].id
            map[Constants.PARAM_ROUTER] = listRouter[indexRouter].id
            map[Constants.PARAM_ROUTER_AMOUNT] = fragUpdateContract_etRouter.text.toString()
            map[Constants.PARAM_CABLE_TYPE] = listOtherCable[indexOtherCable].id
            map[Constants.PARAM_ASSIGN_DATE] = fragUpdateContract_tvFrom.text.toString().convertToDateFormat("")
            map[Constants.PARAM_TO_ASSIGN_DATE] = fragUpdateContract_tvTo.text.toString().convertToDateFormat("")
            map[Constants.PARAM_APPOINTMENT] = updateContractModel.assigndate.getHourFromDate("")
            map[Constants.PARAM_DESCRIPTION] = fragUpdateContract_etNewNote.text.toString().getNotes(fragUpdateContract_tvOldNote.text.toString())
            map[Constants.PARAM_CUS_TYPE] = updateContractModel.custype
            map[Constants.PARAM_STATUS] = listResult[indexResult].id
            map[Constants.PARAM_DESCRIPTION_RD] = fragUpdateContract_etNoteReasonDelay.text.toString()
            map[Constants.PARAM_REASON_DELAY] = listReasonDelay[indexReasonDelay].id
            map[Constants.PARAM_IP_USER] = getSharePreferences().ipAddress
            map[Constants.PARAM_OPTICAL_JUMP] = map[Constants.PARAM_JUMPER_WIRE].toString()
        }
    }

    private fun handleStepUpdate(step: Int) {
        hideAllView()
        dataCore.stepUpdate += step
        when (dataCore.stepUpdate) {
            Constants.STEP_1_UPDATE_CABLE -> {
                fragUpdateContract_llCable.visibility = View.VISIBLE
            }
            Constants.STEP_2_UPDATE_CABLE_INFO -> {
                fragUpdateContract_llCableInfo.visibility = View.VISIBLE
            }
            Constants.STEP_3_UPDATE_MODEM_STB -> {
                fragUpdateContract_llModemStb.visibility = View.VISIBLE
            }
            Constants.STEP_4_UPDATE_REASON_RESULT -> {
                fragUpdateContract_llReasonResult.visibility = View.VISIBLE
            }
        }
        fragUpdateContract_tvStep.text = getString(R.string.step_update, dataCore.stepUpdate, Constants.STEP_4_UPDATE_REASON_RESULT)
        fragUpdateContract_tvBack.visibility = if (dataCore.stepUpdate == Constants.STEP_1_UPDATE_CABLE) View.GONE else View.VISIBLE
        fragUpdateContract_tvNext.text = getString(if (dataCore.stepUpdate == Constants.STEP_4_UPDATE_REASON_RESULT) R.string.update else R.string.next)
    }

    private fun hideAllView() {
        fragUpdateContract_llCable.visibility = View.GONE
        fragUpdateContract_llCableInfo.visibility = View.GONE
        fragUpdateContract_llModemStb.visibility = View.GONE
        fragUpdateContract_llReasonResult.visibility = View.GONE
    }

    private fun initSingleDialog() {
        dataCore.run {
            //thêm dữ liêu Cable
            fragUpdateContract_tvInDoor.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.update_indoor),
                        view = fragUpdateContract_tvInDoor, listData = listInDoor,
                        itemSelected = indexInDoor)
            }
            fragUpdateContract_tvOutDoor.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.update_outdoor),
                        view = fragUpdateContract_tvOutDoor, listData = listOutDoor,
                        itemSelected = indexOutDoor)
            }
            fragUpdateContract_tvOtherCable.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.type_cable_other_cable),
                        view = fragUpdateContract_tvOtherCable, listData = listOtherCable,
                        itemSelected = indexOtherCable)
            }
            fragUpdateContract_tvRouter.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.update_router_amount),
                        view = fragUpdateContract_tvRouter, listData = listRouter,
                        itemSelected = indexRouter)
            }
            //thêm dữ liệu Modem & Stb
            fragUpdateContract_tvModem.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.update_modem),
                        view = fragUpdateContract_tvModem, listData = listModem,
                        itemSelected = indexModem)
            }
            fragUpdateContract_tvTypeModem.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.update_type),
                        view = fragUpdateContract_tvTypeModem, listData = listTypeModem,
                        itemSelected = indexTypeModem)
            }
            fragUpdateContract_tvStb.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.update_stb),
                        view = fragUpdateContract_tvStb, listData = listStb,
                        itemSelected = indexStb)
            }
            fragUpdateContract_tvTypeStb.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.update_type),
                        view = fragUpdateContract_tvTypeStb, listData = listTypeStb,
                        itemSelected = indexTypeStb)
            }
            //thêm dữ liệu other
            fragUpdateContract_tvToHour.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.update_to_hour),
                        view = fragUpdateContract_tvToHour, listData = listToHour,
                        itemSelected = indexToHour)
            }
            fragUpdateContract_tvFromHour.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.update_from_hour),
                        view = fragUpdateContract_tvFromHour, listData = listFromHour,
                        itemSelected = indexFromHour)
            }
            fragUpdateContract_tvReasonDelay.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.update_reason_delay),
                        view = fragUpdateContract_tvReasonDelay, listData = listReasonDelay,
                        itemSelected = indexReasonDelay)
            }
            fragUpdateContract_tvResult.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.update_result), view = fragUpdateContract_tvResult, listData = listResult,
                        itemSelected = indexResult)
            }
        }

    }

    fun setIndexSelected(view: View, position: Int) {
        dataCore.setIndexSelected(view, position)
    }

    private fun setDataToDialog() {
        dataCore.run {
            updateContractModel.run {
                getObjectSingleCable(indtype, listInDoor, fragUpdateContract_tvInDoor)
                getObjectSingleCable(outdtype, listOutDoor, fragUpdateContract_tvOutDoor)
                getObjectSingleCable(cabletype, listOtherCable, fragUpdateContract_tvOtherCable)
                getObjectSingleCable(router, listRouter, fragUpdateContract_tvRouter)
                getObjectSingleCable(modem, listModem, fragUpdateContract_tvModem)
                getObjectSingleCable(modemtype, listTypeModem, fragUpdateContract_tvTypeModem)
                getObjectSingleCable(stb, listStb, fragUpdateContract_tvStb)
                getObjectSingleCable(stbtype, listTypeStb, fragUpdateContract_tvTypeStb)
                getObjectSingleCable(reasondelay, listReasonDelay, fragUpdateContract_tvReasonDelay)
                getObjectSingleCable(status, listResult, fragUpdateContract_tvResult)
            }
        }
    }

    private fun setDetailUpdateToView() {
        dataCore.run {
            updateContractModel.run {
                fragUpdateContract_etInDoor.setText(indoor.toString())
                fragUpdateContract_etOutDoor.setText(outdoor.toString())
                fragUpdateContract_etOtherCable.setText(box.toString())
                fragUpdateContract_etRouter.setText(routeramount.toString())
                fragUpdateContract_etAmountModem.setText(modemamount.toString())
                fragUpdateContract_etAmountStb.setText(stbamount.toString())
                fragUpdateContract_tvOdcCable.text = odccabletype
                fragUpdateContract_tvOldNote.text = note
                fragUpdateContract_tvFrom.text = assigndate.convertToDateFormat("")
                fragUpdateContract_tvTo.text = assigndate1.convertToDateFormat("")
                getHourFromDate(assigndate, fragUpdateContract_tvFromHour, true)
                getHourFromDate(assigndate1, fragUpdateContract_tvToHour, false)
                initCableInfoView(fragUpdateContract_rvMain)
            }
        }
    }

    private fun handleSetDataToView() {
        setDataToDialog()
        setDetailUpdateToView()
        hideLoading()
    }

    override fun loadUpdateContractDeployment(response: ResponseModel) {
//        http://wsfcam.fpt.vn/FCAM.svc/GetDeploymentObject/SIR3-Pitou.Pich/306017/2169492/1157182
        hideLoading()
    }

    override fun loadDetailUpdate(response: ResponseBody) {
        dataCore.updateContractModel = Gson().fromJson(response.string(), UpdateContractModel::class.java)
        handleSetDataToView()
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