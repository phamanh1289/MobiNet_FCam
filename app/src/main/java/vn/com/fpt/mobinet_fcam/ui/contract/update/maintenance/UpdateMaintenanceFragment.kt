package vn.com.fpt.mobinet_fcam.ui.contract.update.maintenance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_update_contract_maintenance.*
import kotlinx.android.synthetic.main.item_cable_info.*
import kotlinx.android.synthetic.main.item_reason_cable_maintenance.*
import kotlinx.android.synthetic.main.item_reason_maintenance.*
import kotlinx.android.synthetic.main.item_reason_note_maintenance.*
import okhttp3.ResponseBody
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.interfaces.ConfirmDialogInterface
import vn.com.fpt.mobinet_fcam.data.network.model.*
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
import vn.com.fpt.mobinet_fcam.utils.AppUtils
import vn.com.fpt.mobinet_fcam.utils.KeyboardUtils
import vn.com.fpt.mobinet_fcam.utils.convertToShortFormat
import vn.com.fpt.mobinet_fcam.utils.getNotes
import javax.inject.Inject

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class UpdateMaintenanceFragment : BaseFragment(), UpdateMaintenanceContract.UpdateMaintenanceView {
    @Inject
    lateinit var presenter: UpdateMaintenancePresenter

    private lateinit var dataCore: DataCoreUpdateContractMaintenance
    var exitUpdate = false

    companion object {
        fun newInstance(item: DetailContractModel, serviceType: Int): UpdateMaintenanceFragment {
            val args = Bundle()
            args.putParcelable(Constants.MODEL, item)
            args.putInt(Constants.PARAM_SERVICE_TYPE, serviceType)
            val fragment = UpdateMaintenanceFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_update_contract_maintenance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {
        setTitle(TitleAndMenuModel(title = getString(R.string.update_contract_maintenance)))
        dataCore = DataCoreUpdateContractMaintenance(context)
        arguments?.let {
            dataCore.infoContract = it.getParcelable(Constants.MODEL)
            dataCore.serviceType = it.getInt(Constants.PARAM_SERVICE_TYPE)
        }
        dataCore.getDefaultDataList()
        handleStepUpdate(Constants.STEP_1_UPDATE_CABLE)
        getDefaultDataList()
        initSingleDialog()
        initOnClick()
        initParamGetDetailUpdate()
    }

    private fun getDefaultDataList() {
        dataCore.run {
            setDefaultFirstItem(listInDoor, fragUpdateContractMaintenance_tvInDoor)
            setDefaultFirstItem(listInDoorGP, fragUpdateContractMaintenance_tvInDoorGp)
            setDefaultFirstItem(listOutDoor, fragUpdateContractMaintenance_tvOutDoor)
            setDefaultFirstItem(listOutDoorGP, fragUpdateContractMaintenance_tvOutDoorGp)
            setDefaultFirstItem(listOtherCable, fragUpdateContractMaintenance_tvOtherCable)
            setDefaultFirstItem(listRouter, fragUpdateContractMaintenance_tvRouterAmount)
            setDefaultFirstItem(listResult, fragUpdateContractMaintenance_tvResult)
            setDefaultFirstItem(listHappenReason, fragUpdateContractMaintenance_tvHappenPosition)
            getListReason(fragUpdateContractMaintenance_tvReason)
            getListReasonDescription(fragUpdateContractMaintenance_tvReasonDescription)
        }
    }

    fun confirmExitUpdate() {
        AppUtils.showDialog(fragmentManager,title = getString(R.string.confirm), content = getString(R.string.mess_exit_update_contract), actionCancel = true, confirmDialogInterface = object : ConfirmDialogInterface {
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
                presenter.getMaintenanceObject(it.mobiaccount, it.password, item.idmain, item.objid)
//                presenter.getMaintenanceObject(it.mobiaccount, it.password, 6910282, 0)
            }
        }
    }

    private fun initOnClick() {
        fragUpdateContractMaintenance_tvNext.setOnClickListener {
            if (dataCore.stepUpdate < Constants.STEP_4_UPDATE_REASON_RESULT)
                handleStepUpdate(Constants.NEXT_STEP_UPDATE)
            else AppUtils.showDialog(fragmentManager,title = getString(R.string.confirm), content = getString(R.string.mess_update_contract), actionCancel = true, confirmDialogInterface = object : ConfirmDialogInterface {
                override fun onClickOk() {
                    if (dataCore.indexResult == Constants.REQUEST_SUCCESS)
                        presenter.let { pre ->
                            showLoading()
                            val map = HashMap<String, Any>()
                            map[Constants.PARAM_CONTRACT_NO] = dataCore.infoContract.contract
                            pre.checkContractHiOpennet(map)
                        }
                    else {
                        showLoading()
                        initParamUpdateContract()
                    }
                }

                override fun onClickCancel() {

                }
            })
        }
        fragUpdateContractMaintenance_tvBack.setOnClickListener {
            if (dataCore.stepUpdate > Constants.STEP_1_UPDATE_CABLE)
                handleStepUpdate(Constants.BACK_STEP_UPDATE)
        }
    }

    private fun addPramsToUpdate(map: HashMap<String, Any>) {
        getDefaultUser()?.run {
            map[Constants.PARAM_USER_NAME_UPPER_FULL.toLowerCase()] = mobiaccount
            map[Constants.PARAM_PASSWORD_UPPER.toLowerCase()] = password
            map[vn.com.fpt.mobinet_fcam.others.constant.Constants.PARAM_UPDATE_BY] = mobiaccount
        }
        dataCore.run {
            addParams(map)
            updateContractModel?.run {
                map[Constants.PARAM_OBJ_ID.toLowerCase()] = objid.toString()
                map[Constants.PARAM_SUP_LIST_ID] = maintenanceid.toString()
                map[Constants.PARAM_FINAL_DES] = fragUpdateContractMaintenance_tvGeneralNote.text.toString().getNotes(fragUpdateContractMaintenance_etNewNote.text.toString())
                map[Constants.PARAM_SERVICE_TYPE.toLowerCase()] = serviceType
                map[Constants.PARAM_IP_USER.toLowerCase()] = getSharePreferences().ipAddress
                map[Constants.PARAM_APPOINTMENT_DATE] = appointment
                map[Constants.PARAM_RESULT] = listResult[indexResult].id
                map[Constants.PARAM_HAPPEN_POSITION] = listHappenReason[indexHappenReason].id
                map[Constants.PARAM_REASON] = listReasonType[indexReasonType].id
                map[Constants.PARAM_INDOOR_TYPE.toLowerCase()] = listInDoor[indexInDoor].id
                map[Constants.PARAM_INDOOR.toLowerCase()] = fragUpdateContractMaintenance_etInDoor.text.toString()
                map[Constants.PARAM_OUTDOOR_TYPE.toLowerCase()] = listOutDoor[indexOutDoor].id
                map[Constants.PARAM_OUTDOOR.toLowerCase()] = fragUpdateContractMaintenance_etOutDoor.text.toString()
                map[Constants.PARAM_CABLE_TYPE.toLowerCase()] = listOtherCable[indexOtherCable].id
                map[Constants.PARAM_BOX.toLowerCase()] = fragUpdateContractMaintenance_etOtherCable.text.toString()
                map[Constants.PARAM_OFTEN_ERROR] = listReasonDescription[indexReasonDescription].id
                map[Constants.PARAM_ROUTER_AMOUNT.toLowerCase()] = fragUpdateContractMaintenance_etRouterAmount.text.toString()
                map[Constants.PARAM_HI_OPEN_NET] = hiOpenNetContract
            }
        }
    }

    private fun handleStepUpdate(step: Int) {
        hideAllView()
        dataCore.stepUpdate += step
        when (dataCore.stepUpdate) {
            Constants.STEP_1_UPDATE_CABLE -> {
                fragUpdateContractMaintenance_llReasonMaintenance.visibility = View.VISIBLE
            }
            Constants.STEP_2_UPDATE_CABLE_INFO -> {
                fragUpdateContractMaintenance_llCable.visibility = View.VISIBLE
            }
            Constants.STEP_3_UPDATE_MODEM_STB -> {
                fragUpdateContractMaintenance_llCableInfo.visibility = View.VISIBLE
            }
            Constants.STEP_4_UPDATE_REASON_RESULT -> {
                fragUpdateContractMaintenance_llReasonNote.visibility = View.VISIBLE
            }
        }
        fragUpdateContractMaintenance_tvStep.text = getString(R.string.step_update, dataCore.stepUpdate, Constants.STEP_4_UPDATE_REASON_RESULT)
        fragUpdateContractMaintenance_tvBack.visibility = if (dataCore.stepUpdate == Constants.STEP_1_UPDATE_CABLE) View.GONE else View.VISIBLE
        fragUpdateContractMaintenance_tvNext.text = getString(if (dataCore.stepUpdate == Constants.STEP_4_UPDATE_REASON_RESULT) R.string.update else R.string.next)
    }

    private fun hideAllView() {
        fragUpdateContractMaintenance_llReasonMaintenance.visibility = View.GONE
        fragUpdateContractMaintenance_llCable.visibility = View.GONE
        fragUpdateContractMaintenance_llCableInfo.visibility = View.GONE
        fragUpdateContractMaintenance_llReasonNote.visibility = View.GONE
    }

    private fun initSingleDialog() {
        dataCore.run {
            fragUpdateContractMaintenance_tvHappenPosition.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.reason_maintenance_happen_position),
                        view = fragUpdateContractMaintenance_tvHappenPosition, listData = listHappenReason,
                        itemSelected = indexHappenReason)
            }
            fragUpdateContractMaintenance_tvReason.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.reason_maintenance_reason),
                        view = fragUpdateContractMaintenance_tvReason, listData = listReasonType,
                        itemSelected = indexReasonType)
            }
            fragUpdateContractMaintenance_tvReasonDescription.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.reason_maintenance_reason_description),
                        view = fragUpdateContractMaintenance_tvReasonDescription, listData = listReasonDescription,
                        itemSelected = indexReasonDescription)
            }
            fragUpdateContractMaintenance_tvInDoor.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.update_indoor),
                        view = fragUpdateContractMaintenance_tvInDoor, listData = listInDoor,
                        itemSelected = indexInDoor)
            }
            fragUpdateContractMaintenance_tvInDoorGp.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.reason_maintenance_indoor_gp),
                        view = fragUpdateContractMaintenance_tvInDoorGp, listData = listInDoorGP,
                        itemSelected = indexInDoorGP)
            }
            fragUpdateContractMaintenance_tvOutDoor.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.update_outdoor),
                        view = fragUpdateContractMaintenance_tvOutDoor, listData = listOutDoor,
                        itemSelected = indexOutDoor)
            }
            fragUpdateContractMaintenance_tvOutDoorGp.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.reason_maintenance_outdoor_gp),
                        view = fragUpdateContractMaintenance_tvOutDoorGp, listData = listOutDoorGP,
                        itemSelected = indexOutDoorGP)
            }
            fragUpdateContractMaintenance_tvOtherCable.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.type_cable_other_cable),
                        view = fragUpdateContractMaintenance_tvOtherCable, listData = listOtherCable,
                        itemSelected = indexOtherCable)
            }
            fragUpdateContractMaintenance_tvRouterAmount.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.update_router_amount),
                        view = fragUpdateContractMaintenance_tvRouterAmount, listData = listRouter,
                        itemSelected = indexRouter)
            }
            fragUpdateContractMaintenance_tvResult.setOnClickListener {
                AppUtils.showDialogSingChoice(
                        fragmentManager = fragmentManager, title = getString(R.string.update_result), view = fragUpdateContractMaintenance_tvResult, listData = listResult,
                        itemSelected = indexResult)
            }
        }
    }

    fun setIndexSelected(view: View, position: Int) {
        dataCore.run {
            setIndexSelected(view, position)
            when (view.id) {
                R.id.fragUpdateContractMaintenance_tvHappenPosition -> {
                    getListReason(fragUpdateContractMaintenance_tvReason)
                    getListReasonDescription(fragUpdateContractMaintenance_tvReasonDescription)
                }
                R.id.fragUpdateContractMaintenance_tvReason -> getListReasonDescription(fragUpdateContractMaintenance_tvReasonDescription)

            }
        }
    }

    private fun setDataToDialog() {
        dataCore.run {
            updateContractModel = listContractModel[Constants.FIRST_ITEM]
            updateContractModel?.run {
                indexInDoor = getObjectSingleCable(indtype, listInDoor, fragUpdateContractMaintenance_tvInDoor)
                indexInDoorGP = getObjectSingleCable(indtypel, listInDoorGP, fragUpdateContractMaintenance_tvInDoorGp)
                indexOutDoor = getObjectSingleCable(outdtype, listOutDoor, fragUpdateContractMaintenance_tvOutDoor)
                indexOutDoorGP = getObjectSingleCable(outdtypel, listOutDoorGP, fragUpdateContractMaintenance_tvOutDoorGp)
                indexOtherCable = getObjectSingleCable(cabletype, listOtherCable, fragUpdateContractMaintenance_tvOtherCable)
                indexRouter = getObjectSingleCable(router, listRouter, fragUpdateContractMaintenance_tvRouterAmount)
                indexResult = getObjectSingleCable(status, listResult, fragUpdateContractMaintenance_tvResult)
                indexHappenReason = getObjectSingleCable(happenposition, listHappenReason, fragUpdateContractMaintenance_tvHappenPosition)
                getListReason(fragUpdateContractMaintenance_tvReason)
                indexReasonType = getObjectSingleCable(reason, listReasonType, fragUpdateContractMaintenance_tvReason)
                getListReasonDescription(fragUpdateContractMaintenance_tvReasonDescription)
                indexReasonDescription = getObjectSingleCable(reasondescription, listReasonDescription, fragUpdateContractMaintenance_tvReasonDescription)
            }
        }
    }

    private fun initParamUpdateContract() {
        presenter.let { pre ->
            val map = HashMap<String, Any>()
            addPramsToUpdate(map)
            pre.postUpdateContractMaintenance(map)
        }
    }

    private fun setDetailUpdateToView() {
        dataCore.run {
            updateContractModel?.run {
                fragUpdateContractMaintenance_etInDoor.setText(indoor.toString())
                fragUpdateContractMaintenance_etIndoorGp.setText(indoorl.toString())
                fragUpdateContractMaintenance_etOutDoor.setText(outdoor.toString())
                fragUpdateContractMaintenance_etOutDoorGp.setText(outdoorl.toString())
                fragUpdateContractMaintenance_etOtherCable.setText(box.toString())
                fragUpdateContractMaintenance_etRouterAmount.setText(routeramount.toString())
                fragUpdateContractMaintenance_tvGeneralNote.text = note
                fragUpdateContractMaintenance_tvAssignDate.text = appointmentdate.convertToShortFormat("")
                dataCore.initCableInfoView(fragUpdateContract_rvMain)
            }
        }
    }

    private fun handleSetDataToView() {
        setDataToDialog()
        setDetailUpdateToView()
        hideLoading()
    }

    private fun getHiOpenNetContract() {
        dataCore.run {
            showDialogHiOpenNet(fragmentManager) {
                showLoading()
                hiOpenNetContract = listHiOpenNet[it].id
                initParamUpdateContract()
            }
        }
        hideLoading()
    }

    override fun loadUpdateContractMaintenance(response: ResponseModel) {
//        http://wsfcam.fpt.vn/FCAM.svc/GetDeploymentObject/SIR3-Pitou.Pich/306017/2169492/1157182
        hideLoading()
    }

    override fun loadContractHiOpennet(response: HiOpenNetModel) {
        if (response.data == Constants.HI_OPEN_NET_NOT_YET)
            getHiOpenNetContract()
        else initParamUpdateContract()
    }

    override fun loadDetailUpdate(response: ResponseBody) {
        dataCore.listContractModel = Gson().fromJson(response.string(), object : TypeToken<ArrayList<UpdateContractModel>>() {}.type)
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