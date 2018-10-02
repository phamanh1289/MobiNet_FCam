package vn.com.fpt.mobinet_fcam.others.datacore

import android.content.Context
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.DetailContractKeyValueModel
import vn.com.fpt.mobinet_fcam.data.network.model.MenuModel
import vn.com.fpt.mobinet_fcam.data.network.model.SingleChoiceModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
object DataCore {
    fun getListMenu(context: Context?): ArrayList<MenuModel> {
        val list = ArrayList<MenuModel>()
        context?.let {
            list.add(MenuModel(title = it.getString(R.string.menu_deployment), image = R.drawable.ic_menu_deployment, type = Constants.MENU_DEPLOYMENT_LIST))
            list.add(MenuModel(title = it.getString(R.string.menu_maintenance), image = R.drawable.ic_menu_maintance, type = Constants.MENU_MAINTENANCE_LIST))
            list.add(MenuModel(title = it.getString(R.string.menu_utilities), image = R.drawable.ic_menu_utilities, type = Constants.MENU_UTILITIES))
            list.add(MenuModel(title = it.getString(R.string.menu_port_net), image = R.drawable.ic_menu_port_net, type = Constants.MENU_PORT_NET))
            list.add(MenuModel(title = it.getString(R.string.menu_report), image = R.drawable.ic_menu_report, type = Constants.MENU_REPORT))
            list.add(MenuModel(title = it.getString(R.string.menu_info), image = R.drawable.ic_menu_info, type = Constants.MENU_INFO))
        }
        return list
    }

    fun getListTypeContract(context: Context?): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            list.add(SingleChoiceModel(id = 1, account = it.getString(R.string.type_contract_adsl)))
            list.add(SingleChoiceModel(id = 2, account = it.getString(R.string.type_contract_ftth)))
        }
        return list
    }

    fun getListCheckType(context: Context?): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            list.add(SingleChoiceModel(id = 1, account = it.getString(R.string.type_contract_normal)))
            list.add(SingleChoiceModel(id = 3, account = it.getString(R.string.type_contract_system)))
        }
        return list
    }

    fun getListDetailContract(context: Context?): ArrayList<DetailContractKeyValueModel> {
        val list = ArrayList<DetailContractKeyValueModel>()
        context?.let {
            list.add(DetailContractKeyValueModel(key = it.getString(R.string.item_result_contract),
                    title = it.getString(R.string.item_result_contract)))
            list.add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_fullname),
                    title = it.getString(R.string.value_detail_contract_fullname)))
            list.add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_address),
                    title = it.getString(R.string.value_detail_contract_address)))
            list.add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_accessname),
                    title = it.getString(R.string.value_detail_contract_accessname)))
            list.add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_assigndate),
                    title = it.getString(R.string.value_detail_contract_assigndate)))
            list.add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_contact),
                    title = it.getString(R.string.value_detail_contract_contract)))
            list.add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_macaddress),
                    title = it.getString(R.string.value_detail_contract_macaddress)))
            list.add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_servicetype),
                    title = it.getString(R.string.value_detail_contract_servicetype)))
            list.add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_area),
                    title = it.getString(R.string.value_detail_contract_area)))
            list.add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_createat),
                    title = it.getString(R.string.value_detail_contract_createat)))
            list.add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_createby),
                    title = it.getString(R.string.value_detail_contract_createby)))
            list.add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_image),
                    title = it.getString(R.string.value_detail_contract_image)))
            list.add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_cusnote),
                    title = it.getString(R.string.value_detail_contract_cusnote)))
        }
        return list
    }
}