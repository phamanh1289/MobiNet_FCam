package vn.com.fpt.mobinet_fcam.ui.contract.update

import android.content.Context
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
import vn.com.fpt.mobinet_fcam.ui.contract.update.adapter.UpdateContractAdapter
import vn.com.fpt.mobinet_fcam.utils.getHourFromDate

/**
 * *******************************************
 * * Created by AnhPT76 on 05/10/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class DataCoreUpdateContract(val context: Context?) {
    lateinit var infoContract: DetailContractModel
    lateinit var updateContractModel: UpdateContractModel
    var listInDoor = ArrayList<SingleChoiceModel>()
    var listOutDoor = ArrayList<SingleChoiceModel>()
    var listOtherCable = ArrayList<SingleChoiceModel>()
    var listRouter = ArrayList<SingleChoiceModel>()
    var listModem = ArrayList<SingleChoiceModel>()
    var listTypeModem = ArrayList<SingleChoiceModel>()
    var listStb = ArrayList<SingleChoiceModel>()
    var listTypeStb = ArrayList<SingleChoiceModel>()
    var listToHour = ArrayList<SingleChoiceModel>()
    var listFromHour = ArrayList<SingleChoiceModel>()
    var listReasonDelay = ArrayList<SingleChoiceModel>()
    var listResult = ArrayList<SingleChoiceModel>()
    var listContractKeyValue = ArrayList<DetailContractKeyValueModel>()
    var adapterCableInfo = UpdateContractAdapter()
    var indexModem = 0
    var indexTypeModem = 0
    var indexStb = 0
    var indexTypeStb = 0
    var indexInDoor = 0
    var indexOutDoor = 0
    var indexOtherCable = 0
    var indexRouter = 0
    var indexReasonDelay = 0
    var indexToHour = 0
    var indexResult = 0
    var indexFromHour = 0
    var serviceType = 0
    var stepUpdate = 0

    fun getDefaultDataList() {
        listInDoor = DataCore.getListCable(context)
        listOutDoor = DataCore.getListCable(context)
        listOtherCable = DataCore.getListOtherCable(context)
        listRouter = DataCore.getListRouter(context)
        listModem = DataCore.getListModem(context)
        listTypeModem = DataCore.getListTypeModem(context)
        listStb = DataCore.getListStb(context)
        listTypeStb = DataCore.getListTypeStb(context)
        listToHour = DataCore.getListHour()
        listFromHour = DataCore.getListHour()
        listReasonDelay = DataCore.getListReason(context)
        listResult = DataCore.getListResult(context)
    }

    fun setDefaultFirstItem(list: ArrayList<SingleChoiceModel>, textView: TextView) {
        list[Constants.FIRST_ITEM].status = true
        textView.text = list[Constants.FIRST_ITEM].account
    }

    fun getObjectSingleCable(id: Int, list: ArrayList<SingleChoiceModel>, textView: TextView) {
        var item = list[Constants.FIRST_ITEM]
        list.forEach {
            if (it.id == id) {
                item.status = false
                it.status = true
                item = it
            }
        }
        textView.text = item.account
    }


    fun addParams(map: HashMap<String, Any>) {
        listContractKeyValue.forEach {
            map[it.key] = it.value
        }
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

    fun getHourFromDate(date: String, textView: TextView, typeList: Boolean) {
        if (date.isNotBlank()) {
            var fromHour = date.getHourFromDate("").toInt()
            val indexList = if (fromHour == Constants.FULL_HOUR) Constants.LAST_INDEX_HOUR else --fromHour
            indexList.let { index ->
                val list = if (typeList) listFromHour else listToHour
                if (typeList) indexFromHour = index else indexToHour = index
                list[Constants.FIRST_ITEM].status = false
                list[index].status = true
                textView.text = list[index].account
            }
        }
    }

    fun initCableInfoView(recyclerView: RecyclerView) {
        listContractKeyValue = DataCore.getListCableInfo(context)
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