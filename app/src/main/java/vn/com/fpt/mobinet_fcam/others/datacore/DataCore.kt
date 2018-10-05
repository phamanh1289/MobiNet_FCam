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
            list.run {
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.type_contract_adsl)))
                add(SingleChoiceModel(id = 2, account = it.getString(R.string.type_contract_ftth)))
            }
        }
        return list
    }

    fun getListCheckType(context: Context?): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            with(list) {
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.type_contract_normal)))
                add(SingleChoiceModel(id = 3, account = it.getString(R.string.type_contract_system)))
            }
        }
        return list
    }

    fun getListDetailContract(context: Context?): ArrayList<DetailContractKeyValueModel> {
        val list = ArrayList<DetailContractKeyValueModel>()
        context?.let {
            list.run {
                add(DetailContractKeyValueModel(key = it.getString(R.string.item_result_contract),
                        title = it.getString(R.string.item_result_contract)))
                add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_fullname),
                        title = it.getString(R.string.value_detail_contract_fullname)))
                add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_address),
                        title = it.getString(R.string.value_detail_contract_address)))
                add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_accessname),
                        title = it.getString(R.string.value_detail_contract_accessname)))
                add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_assigndate),
                        title = it.getString(R.string.value_detail_contract_assigndate)))
                add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_contact),
                        title = it.getString(R.string.value_detail_contract_contract)))
                add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_macaddress),
                        title = it.getString(R.string.value_detail_contract_macaddress)))
                add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_servicetype),
                        title = it.getString(R.string.value_detail_contract_servicetype)))
                add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_area),
                        title = it.getString(R.string.value_detail_contract_area)))
                add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_createat),
                        title = it.getString(R.string.value_detail_contract_createat)))
                add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_createby),
                        title = it.getString(R.string.value_detail_contract_createby)))
                add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_image),
                        title = it.getString(R.string.value_detail_contract_image)))
                add(DetailContractKeyValueModel(key = it.getString(R.string.key_detail_contract_cusnote),
                        title = it.getString(R.string.value_detail_contract_cusnote)))
            }
        }
        return list
    }

    fun getListCableInfo(context: Context?): ArrayList<DetailContractKeyValueModel> {
        val list = ArrayList<DetailContractKeyValueModel>()
        context?.let {
            list.run {
                add(DetailContractKeyValueModel(title = it.getString(R.string.update_rj11), key = it.getString(R.string.params_update_contract_boxlink), property = it.getString(R.string.params_update_contract_boxlink).toLowerCase()))
                add(DetailContractKeyValueModel(title = it.getString(R.string.update_wire), key = it.getString(R.string.update_wire), property = it.getString(R.string.update_wire).toLowerCase()))
                add(DetailContractKeyValueModel(title = it.getString(R.string.update_mang_xoong), key = it.getString(R.string.params_update_contract_aluminumtag), property = it.getString(R.string.update_mang_xoong).replace(" ", "").toLowerCase()))
                add(DetailContractKeyValueModel(title = it.getString(R.string.update_button), key = it.getString(R.string.update_button), property = it.getString(R.string.update_button).toLowerCase()))
                add(DetailContractKeyValueModel(title = it.getString(R.string.update_jumper), key = it.getString(R.string.update_jumper).replace(" ", ""), property = it.getString(R.string.update_jumper).replace(" ", "").toLowerCase()))
                add(DetailContractKeyValueModel(title = it.getString(R.string.update_onu), key = it.getString(R.string.update_onu), property = it.getString(R.string.update_onu).toLowerCase()))
                add(DetailContractKeyValueModel(title = it.getString(R.string.update_box_ftth), key = it.getString(R.string.update_box_ftth).replace(" ", ""), property = it.getString(R.string.update_box_ftth).replace(" ", "").toLowerCase()))
                add(DetailContractKeyValueModel(title = it.getString(R.string.update_tie_wire), key = it.getString(R.string.params_update_contract_sticking), property = it.getString(R.string.params_update_contract_sticking).toLowerCase()))
                add(DetailContractKeyValueModel(title = it.getString(R.string.update_sc_sc), key = it.getString(R.string.update_sc_sc), property = it.getString(R.string.update_sc_sc).replace("-", "").toLowerCase()))
                add(DetailContractKeyValueModel(title = it.getString(R.string.update_fc_sc_sc), key = it.getString(R.string.params_update_contract_fast_connect), property = it.getString(R.string.params_update_contract_fast_connect).toLowerCase()))
                add(DetailContractKeyValueModel(title = it.getString(R.string.update_fc_sc_apc), key = it.getString(R.string.params_update_contract_fast_connect_apc), property = it.getString(R.string.params_update_contract_fast_connect_apc).toLowerCase()))
                add(DetailContractKeyValueModel(title = it.getString(R.string.update_optical_fiber), key = it.getString(R.string.params_update_contract_optical_bedbug), property = it.getString(R.string.update_optical_fiber).replace(" ", "").toLowerCase()))
                add(DetailContractKeyValueModel(title = it.getString(R.string.update_tube), key = it.getString(R.string.update_tube), property = it.getString(R.string.update_tube).toLowerCase()))
            }
        }
        return list
    }

    fun getListCable(context: Context?): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            list.run {
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.type_cable_05_mm)))
                add(SingleChoiceModel(id = 2, account = it.getString(R.string.type_cable_1FO)))
                add(SingleChoiceModel(id = 3, account = it.getString(R.string.type_cable_2FO)))
                add(SingleChoiceModel(id = 4, account = it.getString(R.string.type_cable_4FO)))
            }
        }
        return list
    }

    fun getListOtherCable(context: Context?): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            list.run {
                add(SingleChoiceModel(id = 100, account = it.getString(R.string.type_cable_utf_cat5)))
                add(SingleChoiceModel(id = 199, account = it.getString(R.string.type_cable_other_cable)))
            }
        }
        return list
    }

    fun getListRouter(context: Context?): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            list.run {
                add(SingleChoiceModel(id = 122, account = it.getString(R.string.type_router_rb750)))
                add(SingleChoiceModel(id = 132, account = it.getString(R.string.type_router_n302r)))
                add(SingleChoiceModel(id = 142, account = it.getString(R.string.type_router_wr741nd)))
                add(SingleChoiceModel(id = 222, account = it.getString(R.string.type_cable_other_cable)))
            }
        }
        return list
    }

    fun getListModem(context: Context?): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            list.run {
                add(SingleChoiceModel(id = 0, account = it.getString(R.string.item_modem_not_get_modem)))
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.item_modem_rent_modem)))
                add(SingleChoiceModel(id = 2, account = it.getString(R.string.item_modem_gift_modem)))
                add(SingleChoiceModel(id = 3, account = it.getString(R.string.item_modem_sale_modem)))
            }
        }
        return list
    }

    fun getListTypeModem(context: Context?): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            list.run {
                add(SingleChoiceModel(id = 0, account = it.getString(R.string.type_modem_not_modem)))
                add(SingleChoiceModel(id = 52, account = it.getString(R.string.type_modem_wifi)))
                add(SingleChoiceModel(id = 42, account = it.getString(R.string.type_modem_4port)))
                add(SingleChoiceModel(id = 112, account = it.getString(R.string.type_modem_adsl2)))
                add(SingleChoiceModel(id = 200, account = it.getString(R.string.type_modem_ftth)))
                add(SingleChoiceModel(id = 300, account = it.getString(R.string.type_modem_ftth_wifi)))
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.type_modem_na)))
            }
        }
        return list
    }

    fun getListStb(context: Context?): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            list.run {
                add(SingleChoiceModel(id = 0, account = it.getString(R.string.item_stb_not_get_stb)))
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.item_stb_rent_stb)))
                add(SingleChoiceModel(id = 2, account = it.getString(R.string.item_stb_gif_stb)))
                add(SingleChoiceModel(id = 3, account = it.getString(R.string.item_stb_sale_stb)))
            }
        }
        return list
    }

    fun getListTypeStb(context: Context?): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            list.run {
                add(SingleChoiceModel(id = 0, account = it.getString(R.string.type_stb_not_stb)))
                add(SingleChoiceModel(id = 100, account = it.getString(R.string.type_stb_coship)))
                add(SingleChoiceModel(id = 200, account = it.getString(R.string.type_stb_comtrend)))
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.type_modem_na)))
            }
        }
        return list
    }

    fun getListReason(context: Context?): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            list.run {
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.reason_delay_waiting)))
                add(SingleChoiceModel(id = 2, account = it.getString(R.string.reason_delay_customer_delay)))
                add(SingleChoiceModel(id = 3, account = it.getString(R.string.reason_delay_over_cable)))
                add(SingleChoiceModel(id = 4, account = it.getString(R.string.reason_delay_hung_up)))
                add(SingleChoiceModel(id = 5, account = it.getString(R.string.reason_delay_customer_transfer)))
                add(SingleChoiceModel(id = 6, account = it.getString(R.string.reason_delay_must_install)))
                add(SingleChoiceModel(id = 7, account = it.getString(R.string.reason_delay_survey_error)))
                add(SingleChoiceModel(id = 8, account = it.getString(R.string.reason_delay_over_ring)))
                add(SingleChoiceModel(id = 9, account = it.getString(R.string.reason_delay_group_point)))
                add(SingleChoiceModel(id = 10, account = it.getString(R.string.reason_delay_lack_of_equipment)))
                add(SingleChoiceModel(id = 11, account = it.getString(R.string.reason_delay_clash_various)))
            }
        }
        return list
    }

    fun getListResult(context: Context?): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            list.run {
                add(SingleChoiceModel(id = 0, account = it.getString(R.string.result_processing)))
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.result_complete)))
            }
        }
        return list
    }

    fun getListHour(): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        for (i in 0 until 24) {
            list.add(SingleChoiceModel(id = i + 1, account = "${i + 1}"))
        }
        return list
    }
}