package vn.com.fpt.mobinet_fcam.ui.contract.update.maintenance

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.DetailContractKeyValueModel
import vn.com.fpt.mobinet_fcam.data.network.model.DetailContractModel
import vn.com.fpt.mobinet_fcam.data.network.model.SingleChoiceModel
import vn.com.fpt.mobinet_fcam.data.network.model.UpdateContractModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.others.datacore.DataCore
import vn.com.fpt.mobinet_fcam.others.dialog.hiOpenNet.HiOpenNetDialog
import vn.com.fpt.mobinet_fcam.ui.contract.update.adapter.UpdateContractAdapter

/**
 * *******************************************
 * * Created by AnhPT76 on 05/10/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class DataCoreUpdateContractMaintenance(val context: Context?) {

    companion object {
        const val TYPE_ADSL = 1
    }

    lateinit var infoContract: DetailContractModel
    var updateContractModel: UpdateContractModel? = null
    var listContractModel = ArrayList<UpdateContractModel>()
    var listHappenReason = ArrayList<SingleChoiceModel>()
    var listReasonType = ArrayList<SingleChoiceModel>()
    var listReasonDescription = ArrayList<SingleChoiceModel>()
    var listInDoor = ArrayList<SingleChoiceModel>()
    var listOutDoor = ArrayList<SingleChoiceModel>()
    var listOutDoorGP = ArrayList<SingleChoiceModel>()
    var listInDoorGP = ArrayList<SingleChoiceModel>()
    var listOtherCable = ArrayList<SingleChoiceModel>()
    var listResult = ArrayList<SingleChoiceModel>()
    var listRouter = ArrayList<SingleChoiceModel>()
    var listHiOpenNet = ArrayList<SingleChoiceModel>()
    var listContractKeyValue = ArrayList<DetailContractKeyValueModel>()
    var adapterCableInfo = UpdateContractAdapter()
    var indexInDoor = 0
    var indexInDoorGP = 0
    var indexHiOpenNet = 0
    var indexOutDoor = 0
    var indexOutDoorGP = 0
    var indexOtherCable = 0
    var indexRouter = 0
    var indexResult = 0
    var indexHappenReason = 0
    var indexReasonType = 0
    var indexReasonDescription = 0
    var serviceType = 0
    var stepUpdate = 0
    var hiOpenNetContract = 0

    fun getDefaultDataList() {
        listInDoor = DataCore.getListCableMaintenance(context)
        listOutDoor = DataCore.getListCableMaintenance(context)
        listOutDoorGP = DataCore.getListCableMaintenance(context)
        listInDoorGP = DataCore.getListCableMaintenance(context)
        listOtherCable = DataCore.getListOtherCable(context)
        listRouter = DataCore.getListRouter(context)
        listResult = DataCore.getListResult(context)
        listHappenReason = if (serviceType == TYPE_ADSL) DataCore.getListHappenADSL(context) else DataCore.getListHappenFTTH(context)
    }

    fun getListReason(textView: TextView) {
        indexReasonType = 0
        listReasonType = DataCore.getListReasonType(context, listHappenReason[indexHappenReason].id)
        setDefaultFirstItem(listReasonType, textView)
    }

    fun getListReasonDescription(textView: TextView) {
        indexReasonDescription = 0
        listReasonDescription = DataCore.getListReasonDescription(context, listReasonType[indexReasonType].id)
        setDefaultFirstItem(listReasonDescription, textView)
    }

    fun setDefaultFirstItem(list: ArrayList<SingleChoiceModel>, textView: TextView) {
        list[Constants.FIRST_ITEM].status = true
        textView.text = list[Constants.FIRST_ITEM].account
    }

    fun getObjectSingleCable(id: Int, list: ArrayList<SingleChoiceModel>, textView: TextView): Int {
        var result = Constants.FIRST_ITEM
        var item = list[result]
        for (i in 0 until list.size) {
            val it = list[i]
            if (it.id == id) {
                item.status = false
                it.status = true
                item = it
                result = i
            }
        }
        textView.text = item.account
        return result
    }

    fun showDialogHiOpenNet(fragmentManager: FragmentManager?, onClick: (Int) -> Unit) {
        indexHiOpenNet = 0
        listHiOpenNet = DataCore.getListHiOpenNet(context)
        listHiOpenNet[indexHiOpenNet].status = true
        fragmentManager?.let {
            val dialog = HiOpenNetDialog()
            dialog.setDataDialog(listHiOpenNet, onClick)
            if (!it.isStateSaved)
                dialog.show(it, HiOpenNetDialog::class.java.simpleName)
        }
    }

    fun addParams(map: HashMap<String, Any>) {
        listContractKeyValue.forEach {
            map[it.key.toLowerCase()] = it.value
        }
    }

    fun setIndexSelected(view: View, position: Int) {
        when (view.id) {
            R.id.fragUpdateContractMaintenance_tvInDoor -> indexInDoor = position
            R.id.fragUpdateContractMaintenance_tvInDoorGp -> indexInDoorGP = position
            R.id.fragUpdateContractMaintenance_tvOutDoor -> indexOutDoor = position
            R.id.fragUpdateContractMaintenance_tvOutDoorGp -> indexOutDoorGP = position
            R.id.fragUpdateContractMaintenance_tvOtherCable -> indexOtherCable = position
            R.id.fragUpdateContractMaintenance_tvRouterAmount -> indexRouter = position
            R.id.fragUpdateContractMaintenance_tvResult -> indexResult = position
            R.id.fragUpdateContractMaintenance_tvHappenPosition -> indexHappenReason = position
            R.id.fragUpdateContractMaintenance_tvReason -> indexReasonType = position
            R.id.fragUpdateContractMaintenance_tvReasonDescription -> indexReasonDescription = position
        }
    }

    fun initCableInfoView(recyclerView: RecyclerView) {
        listContractKeyValue = DataCore.getListCableInfo(context, false)
        recyclerView.apply {
            val layout = LinearLayoutManager(context)
            layoutManager = layout
            setHasFixedSize(true)
            adapter = adapterCableInfo
        }
        setDataToRecyclerView()
        adapterCableInfo.submitList(listContractKeyValue)
        adapterCableInfo.notifyDataSetChanged()
    }

    private fun setDataToRecyclerView() {
        val map: HashMap<String, Any> = Gson().fromJson(Gson().toJson(updateContractModel), object : TypeToken<HashMap<String, Any>>() {}.type)
        listContractKeyValue.forEach { item ->
            map.forEach {
                if (it.key == item.property)
                    item.value = (it.value as Double).toInt().toString()
            }
        }
        adapterCableInfo.submitList(listContractKeyValue)
        adapterCableInfo.notifyDataSetChanged()
    }
}