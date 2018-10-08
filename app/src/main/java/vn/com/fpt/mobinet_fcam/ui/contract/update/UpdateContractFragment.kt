package vn.com.fpt.mobinet_fcam.ui.contract.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_update_contract.*
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
class UpdateContractFragment : BaseFragment(), UpdateContractContract.UpdateContractView {
    @Inject
    lateinit var presenter: UpdateContractPresenter

    private lateinit var dataCore: DataCoreUpdateContract
    var exitUpdate = false

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
        dataCore.setDefaultFirstItem(dataCore.listInDoor, fragUpdateContract_tvInDoor)
        dataCore.setDefaultFirstItem(dataCore.listOutDoor, fragUpdateContract_tvOutDoor)
        dataCore.setDefaultFirstItem(dataCore.listOtherCable, fragUpdateContract_tvOtherCable)
        dataCore.setDefaultFirstItem(dataCore.listRouter, fragUpdateContract_tvRouter)
        dataCore.setDefaultFirstItem(dataCore.listModem, fragUpdateContract_tvModem)
        dataCore.setDefaultFirstItem(dataCore.listTypeModem, fragUpdateContract_tvTypeModem)
        dataCore.setDefaultFirstItem(dataCore.listStb, fragUpdateContract_tvStb)
        dataCore.setDefaultFirstItem(dataCore.listTypeStb, fragUpdateContract_tvTypeStb)
        dataCore.setDefaultFirstItem(dataCore.listToHour, fragUpdateContract_tvToHour)
        dataCore.setDefaultFirstItem(dataCore.listFromHour, fragUpdateContract_tvFromHour)
        dataCore.setDefaultFirstItem(dataCore.listReasonDelay, fragUpdateContract_tvReasonDelay)
        dataCore.setDefaultFirstItem(dataCore.listResult, fragUpdateContract_tvResult)
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
        showLoading()
        getDefaultUser()?.let {
            //            presenter.getDetailUpdate(it.mobiaccount, it.password, 2171722, 1614502)
            presenter.getDetailUpdate(it.mobiaccount, it.password, dataCore.infoContract.deployid, dataCore.infoContract.objid)
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
        dataCore.addParams(map)
        getDefaultUser()?.let {
            map[Constants.PARAM_USER_NAME_UPPER] = it.mobiaccount
            map[Constants.PARAM_PASSWORD_UPPER] = it.password
        }
        dataCore.let {
            map[Constants.PARAM_OBJ_ID] = it.updateContractModel.objid
            map[Constants.PARAM_SERVICE_TYPE] = it.serviceType
            map[Constants.PARAM_SUP_INF_ID] = it.updateContractModel.deployid
            map[Constants.PARAM_CREATE_BY] = it.infoContract.createby
            map[Constants.PARAM_OUTDOOR] = fragUpdateContract_etOutDoor.text.toString()
            map[Constants.PARAM_OUTDOOR_TYPE] = it.listOutDoor[it.indexOutDoor].id
            map[Constants.PARAM_INDOOR] = fragUpdateContract_etInDoor.text.toString()
            map[Constants.PARAM_INDOOR_TYPE] = it.listInDoor[it.indexInDoor].id
            map[Constants.PARAM_ROUTER] = it.listRouter[it.indexRouter].id
            map[Constants.PARAM_ROUTER_AMOUNT] = fragUpdateContract_etRouter.text.toString()
            map[Constants.PARAM_CABLE_TYPE] = it.listOtherCable[it.indexOtherCable].id
            map[Constants.PARAM_ASSIGN_DATE] = fragUpdateContract_tvFrom.text.toString().convertToDateFormat("")
            map[Constants.PARAM_TO_ASSIGN_DATE] = fragUpdateContract_tvTo.text.toString().convertToDateFormat("")
            map[Constants.PARAM_APPOINTMENT] = it.updateContractModel.assigndate.getHourFromDate("")
            map[Constants.PARAM_DESCRIPTION] = fragUpdateContract_etNewNote.text.toString().getNotes(fragUpdateContract_tvOldNote.text.toString())
            map[Constants.PARAM_CUS_TYPE] = it.updateContractModel.custype
            map[Constants.PARAM_STATUS] = it.listResult[it.indexResult].id
            map[Constants.PARAM_DESCRIPTION_RD] = fragUpdateContract_etNoteReasonDelay.text.toString()
            map[Constants.PARAM_REASON_DELAY] = it.listReasonDelay[it.indexReasonDelay].id
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
        //thêm dữ liêu Cable
        fragUpdateContract_tvInDoor.setOnClickListener {
            AppUtils.showDialogSingChoice(
                    fragmentManager = fragmentManager, title = getString(R.string.update_indoor),
                    view = fragUpdateContract_tvInDoor, listData = dataCore.listInDoor,
                    itemSelected = dataCore.indexInDoor)
        }
        fragUpdateContract_tvOutDoor.setOnClickListener {
            AppUtils.showDialogSingChoice(
                    fragmentManager = fragmentManager, title = getString(R.string.update_outdoor),
                    view = fragUpdateContract_tvOutDoor, listData = dataCore.listOutDoor,
                    itemSelected = dataCore.indexOutDoor)
        }
        fragUpdateContract_tvOtherCable.setOnClickListener {
            AppUtils.showDialogSingChoice(
                    fragmentManager = fragmentManager, title = getString(R.string.type_cable_other_cable),
                    view = fragUpdateContract_tvOtherCable, listData = dataCore.listOtherCable,
                    itemSelected = dataCore.indexOtherCable)
        }
        fragUpdateContract_tvRouter.setOnClickListener {
            AppUtils.showDialogSingChoice(
                    fragmentManager = fragmentManager, title = getString(R.string.update_router_amount),
                    view = fragUpdateContract_tvRouter, listData = dataCore.listRouter,
                    itemSelected = dataCore.indexRouter)
        }
        //thêm dữ liệu Modem & Stb
        fragUpdateContract_tvModem.setOnClickListener {
            AppUtils.showDialogSingChoice(
                    fragmentManager = fragmentManager, title = getString(R.string.update_modem),
                    view = fragUpdateContract_tvModem, listData = dataCore.listModem,
                    itemSelected = dataCore.indexModem)
        }
        fragUpdateContract_tvTypeModem.setOnClickListener {
            AppUtils.showDialogSingChoice(
                    fragmentManager = fragmentManager, title = getString(R.string.update_type),
                    view = fragUpdateContract_tvTypeModem, listData = dataCore.listTypeModem,
                    itemSelected = dataCore.indexTypeModem)
        }
        fragUpdateContract_tvStb.setOnClickListener {
            AppUtils.showDialogSingChoice(
                    fragmentManager = fragmentManager, title = getString(R.string.update_stb),
                    view = fragUpdateContract_tvStb, listData = dataCore.listStb,
                    itemSelected = dataCore.indexStb)
        }
        fragUpdateContract_tvTypeStb.setOnClickListener {
            AppUtils.showDialogSingChoice(
                    fragmentManager = fragmentManager, title = getString(R.string.update_type),
                    view = fragUpdateContract_tvTypeStb, listData = dataCore.listTypeStb,
                    itemSelected = dataCore.indexTypeStb)
        }
        //thêm dữ liệu other
        fragUpdateContract_tvToHour.setOnClickListener {
            AppUtils.showDialogSingChoice(
                    fragmentManager = fragmentManager, title = getString(R.string.update_to_hour),
                    view = fragUpdateContract_tvToHour, listData = dataCore.listToHour,
                    itemSelected = dataCore.indexToHour)
        }
        fragUpdateContract_tvFromHour.setOnClickListener {
            AppUtils.showDialogSingChoice(
                    fragmentManager = fragmentManager, title = getString(R.string.update_from_hour),
                    view = fragUpdateContract_tvFromHour, listData = dataCore.listFromHour,
                    itemSelected = dataCore.indexFromHour)
        }
        fragUpdateContract_tvReasonDelay.setOnClickListener {
            AppUtils.showDialogSingChoice(
                    fragmentManager = fragmentManager, title = getString(R.string.update_reason_delay),
                    view = fragUpdateContract_tvReasonDelay, listData = dataCore.listReasonDelay,
                    itemSelected = dataCore.indexReasonDelay)
        }
        fragUpdateContract_tvResult.setOnClickListener {
            AppUtils.showDialogSingChoice(
                    fragmentManager = fragmentManager, title = getString(R.string.update_result), view = fragUpdateContract_tvResult, listData = dataCore.listResult,
                    itemSelected = dataCore.indexResult)
        }
    }

    fun setIndexSelected(view: View, position: Int) {
        dataCore.setIndexSelected(view, position)
    }

    private fun setDataToDialog() {
        dataCore.updateContractModel.let {
            dataCore.getObjectSingleCable(it.indtype, dataCore.listInDoor, fragUpdateContract_tvInDoor)
            dataCore.getObjectSingleCable(it.outdtype, dataCore.listOutDoor, fragUpdateContract_tvOutDoor)
            dataCore.getObjectSingleCable(it.cabletype, dataCore.listOtherCable, fragUpdateContract_tvOtherCable)
            dataCore.getObjectSingleCable(it.router, dataCore.listRouter, fragUpdateContract_tvRouter)
            dataCore.getObjectSingleCable(it.modem, dataCore.listModem, fragUpdateContract_tvModem)
            dataCore.getObjectSingleCable(it.modemtype, dataCore.listTypeModem, fragUpdateContract_tvTypeModem)
            dataCore.getObjectSingleCable(it.stb, dataCore.listStb, fragUpdateContract_tvStb)
            dataCore.getObjectSingleCable(it.stbtype, dataCore.listTypeStb, fragUpdateContract_tvTypeStb)
            dataCore.getObjectSingleCable(it.reasondelay, dataCore.listReasonDelay, fragUpdateContract_tvReasonDelay)
            dataCore.getObjectSingleCable(it.status, dataCore.listResult, fragUpdateContract_tvResult)
        }
    }

    private fun setDetailUpdateToView() {
        dataCore.updateContractModel.let {
            fragUpdateContract_etInDoor.setText(it.indoor.toString())
            fragUpdateContract_etOutDoor.setText(it.outdoor.toString())
            fragUpdateContract_etOtherCable.setText(it.box.toString())
            fragUpdateContract_etRouter.setText(it.routeramount.toString())
            fragUpdateContract_etAmountModem.setText(it.modemamount.toString())
            fragUpdateContract_etAmountStb.setText(it.stbamount.toString())
            fragUpdateContract_tvOdcCable.text = it.odccabletype
            fragUpdateContract_tvOldNote.text = it.note
            fragUpdateContract_tvFrom.text = it.assigndate.convertToDateFormat("")
            fragUpdateContract_tvTo.text = it.assigndate1.convertToDateFormat("")
            dataCore.getHourFromDate(it.assigndate,fragUpdateContract_tvFromHour,true)
            dataCore.getHourFromDate(it.assigndate1,fragUpdateContract_tvToHour,false)
            dataCore.initCableInfoView(fragUpdateContract_rvMain)
        }
    }

    private fun handleSetDataToView() {
        setDataToDialog()
        setDetailUpdateToView()
        hideLoading()
    }

    override fun loadUpdateContractDeployment(response: ResponseModel) {
//        http://wsfcam.fpt.vn/FCAM.svc/GetDeploymentObject/SIR3-Pitou.Pich/306017/2169492/1157182
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