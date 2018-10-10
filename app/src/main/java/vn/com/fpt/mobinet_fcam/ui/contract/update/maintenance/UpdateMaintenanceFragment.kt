package vn.com.fpt.mobinet_fcam.ui.contract.update.maintenance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_update_contract_maintenance.*
import kotlinx.android.synthetic.main.item_cable.*
import kotlinx.android.synthetic.main.item_cable_info.*
import kotlinx.android.synthetic.main.item_reason_cable_maintenance.*
import kotlinx.android.synthetic.main.item_reason_maintenance.*
import kotlinx.android.synthetic.main.item_reason_note_maintenance.*
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
//                                presenter.getMaintenanceObject(it.mobiaccount, it.password, item.idmain, item.objid)
                presenter.getMaintenanceObject(it.mobiaccount, it.password, 6907302, 196872)
            }
        }
    }

    private fun initOnClick() {
        fragUpdateContractMaintenance_tvNext.setOnClickListener {
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
        fragUpdateContractMaintenance_tvBack.setOnClickListener {
            if (dataCore.stepUpdate > Constants.STEP_1_UPDATE_CABLE)
                handleStepUpdate(Constants.BACK_STEP_UPDATE)
        }
    }

    private fun addPramsToUpdate(map: HashMap<String, Any>) {
        dataCore.addParams(map)
        getDefaultUser()?.let {
            map[Constants.PARAM_USER_NAME_UPPER] = it.mobiaccount
            map[Constants.PARAM_PASSWORD_UPPER] = it.password
        }
        dataCore.run {
            map[Constants.PARAM_OBJ_ID] = updateContractModel?.objid.toString()
            map[Constants.PARAM_SERVICE_TYPE] = serviceType
            map[Constants.PARAM_SUP_INF_ID] = updateContractModel?.deployid.toString()
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
            map[Constants.PARAM_APPOINTMENT] = updateContractModel?.assigndate.getHourFromDate("")
            map[Constants.PARAM_DESCRIPTION] = fragUpdateContract_etNewNote.text.toString().getNotes(fragUpdateContract_tvOldNote.text.toString())
            map[Constants.PARAM_CUS_TYPE] = updateContractModel?.custype.toString()
            map[Constants.PARAM_STATUS] = listResult[indexResult].id
            map[Constants.PARAM_DESCRIPTION_RD] = fragUpdateContract_etNoteReasonDelay.text.toString()
            map[Constants.PARAM_IP_USER] = getSharePreferences().ipAddress
            map[Constants.PARAM_OPTICAL_JUMP] = map[Constants.PARAM_JUMPER_WIRE].toString()
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
            updateContractModel = Gson().fromJson("{\n" +
                    "    \"maintenanceid\": 6907302,\n" +
                    "    \"objid\": 196872,\n" +
                    "    \"happenposition\": 14,\n" +
                    "    \"reason\": 30,\n" +
                    "    \"reasondescription\": 1,\n" +
                    "    \"indtype\": 202,\n" +
                    "    \"indoor\": 202,\n" +
                    "    \"outdtype\": 212,\n" +
                    "    \"outdoor\": 212,\n" +
                    "    \"indoorl\": 232,\n" +
                    "    \"outdoorl\": 242,\n" +
                    "    \"indtypel\": 232,\n" +
                    "    \"outdtypel\": 242,\n" +
                    "    \"cabletype\": 199,\n" +
                    "    \"box\": 199,\n" +
                    "    \"router\": 142,\n" +
                    "    \"routeramount\": 142,\n" +
                    "    \"boxlink\": 1,\n" +
                    "    \"wire\": 2,\n" +
                    "    \"button\": 3,\n" +
                    "    \"aluminumtag\": 4,\n" +
                    "    \"mangxoong01fo\": 5,\n" +
                    "    \"jumperwire\": 6,\n" +
                    "    \"wistickingplastere\": 7,\n" +
                    "    \"onu\": 8,\n" +
                    "    \"boxftth\": 9,\n" +
                    "    \"tube\": 10,\n" +
                    "    \"scsc\": 11,\n" +
                    "    \"opticalfiber\": 12,\n" +
                    "    \"fastconnector\": 13,\n" +
                    "    \"fastconnectorapc\": 14,\n" +
                    "    \"appointmentdate\": \"10/10/2018 12:00:00 PM\",\n" +
                    "    \"note\": \"> >reaksame.liv(2018-10-10 09:11:37):>>problem error cable and homes customers fix cable news now internet ok\\r\\nMs.  Ier Sokhmouy\\r\\nNumber.  012898922\\r\\nAdd cable 10m\\r\\nButton 4\\r\\nRj11 1\"\n" +
                    "  }",UpdateContractModel::class.java)
//            updateContractModel = listContractModel[Constants.FIRST_ITEM]
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

    override fun loadUpdateContractMaintenance(response: ResponseModel) {
//        http://wsfcam.fpt.vn/FCAM.svc/GetDeploymentObject/SIR3-Pitou.Pich/306017/2169492/1157182
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