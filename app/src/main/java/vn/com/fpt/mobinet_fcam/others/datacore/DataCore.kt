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

    fun getListDetailContractDeployment(context: Context?): ArrayList<DetailContractKeyValueModel> {
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

    fun getListDetailContractMaintenance(context: Context?): ArrayList<DetailContractKeyValueModel> {
        val list = ArrayList<DetailContractKeyValueModel>()
        context?.let {
            list.run {
                add(DetailContractKeyValueModel(title = it.getString(R.string.item_result_contract),
                        key = it.getString(R.string.item_result_contract)))
                add(DetailContractKeyValueModel(title = it.getString(R.string.value_detail_contract_accessname),
                        key = it.getString(R.string.key_detail_contract_name)))
                add(DetailContractKeyValueModel(title = it.getString(R.string.value_detail_contract_fullname),
                        key = it.getString(R.string.key_detail_contract_fullname)))
                add(DetailContractKeyValueModel(title = it.getString(R.string.value_detail_contract_phone),
                        key = it.getString(R.string.key_detail_contract_location_phone)))
                add(DetailContractKeyValueModel(title = it.getString(R.string.value_detail_contract_address),
                        key = it.getString(R.string.key_detail_contract_address)))
                add(DetailContractKeyValueModel(title = it.getString(R.string.value_detail_contract_odc_cable),
                        key = it.getString(R.string.key_detail_contract_odc_cable_type)))
                add(DetailContractKeyValueModel(title = it.getString(R.string.value_detail_contract_first_status),
                        key = it.getString(R.string.key_detail_contract_first_status)))
                add(DetailContractKeyValueModel(title = it.getString(R.string.value_detail_contract_servicetype),
                        key = it.getString(R.string.key_detail_contract_servicetype)))
                add(DetailContractKeyValueModel(title = it.getString(R.string.item_result_create_date),
                        key = it.getString(R.string.key_detail_contract_date)))
                add(DetailContractKeyValueModel(title = it.getString(R.string.value_detail_contract_cable_outdoor),
                        key = it.getString(R.string.key_detail_contract_od_cable)))
                add(DetailContractKeyValueModel(title = it.getString(R.string.value_detail_contract_cable_indoor),
                        key = it.getString(R.string.key_detail_contract_id_cable)))
                add(DetailContractKeyValueModel(title = it.getString(R.string.value_detail_contract_call_note),
                        key = it.getString(R.string.key_detail_contract_init_desc)))
                add(DetailContractKeyValueModel(title = it.getString(R.string.value_detail_contract_checklist),
                        key = it.getString(R.string.key_detail_contract_total_checklist)))
                add(DetailContractKeyValueModel(title = it.getString(R.string.value_detail_contract_description),
                        key = it.getString(R.string.key_detail_contract_description)))
            }
        }
        return list
    }

    fun getListCableInfo(context: Context?, typeContract: Boolean): ArrayList<DetailContractKeyValueModel> {
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
                //true : property theo Deployment contract
                //false : property theo Maintenance contract
                add(DetailContractKeyValueModel(title = it.getString(R.string.update_tie_wire), key = it.getString(R.string.params_update_contract_sticking), property = it.getString(if (typeContract) R.string.params_update_contract_sticking else R.string.params_update_contract_wisticking).toLowerCase()))
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

    fun getListCableMaintenance(context: Context?): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            list.run {
                add(SingleChoiceModel(id = 32, account = it.getString(R.string.type_cable_cable5)))
                add(SingleChoiceModel(id = 202, account = it.getString(R.string.type_cable_fo_1)))
                add(SingleChoiceModel(id = 212, account = it.getString(R.string.type_cable_fo_2)))
                add(SingleChoiceModel(id = 222, account = it.getString(R.string.type_cable_fo_4)))
                add(SingleChoiceModel(id = 232, account = it.getString(R.string.type_cable_fo_8)))
                add(SingleChoiceModel(id = 242, account = it.getString(R.string.type_cable_fo_16)))
            }
        }
        return list
    }

    fun getListHappenFTTH(context: Context?): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            list.run {
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.happen_ftth_in_customer)))
                add(SingleChoiceModel(id = 2, account = it.getString(R.string.happen_ftth_infrastructure)))
                add(SingleChoiceModel(id = 3, account = it.getString(R.string.happen_ftth_group_point)))
                add(SingleChoiceModel(id = 4, account = it.getString(R.string.happen_ftth_wire_from)))
                add(SingleChoiceModel(id = 5, account = it.getString(R.string.happen_ftth_in_odf)))
                add(SingleChoiceModel(id = 6, account = it.getString(R.string.happen_ftth_system_error)))
                add(SingleChoiceModel(id = 7, account = it.getString(R.string.happen_ftth_module_fiber)))
                add(SingleChoiceModel(id = 8, account = it.getString(R.string.happen_ftth_switch)))
                add(SingleChoiceModel(id = 9, account = it.getString(R.string.happen_ftth_noc)))
                add(SingleChoiceModel(id = 10, account = it.getString(R.string.happen_ftth_not_fix)))
                add(SingleChoiceModel(id = 11, account = it.getString(R.string.happen_ftth_other_reason)))
                add(SingleChoiceModel(id = 12, account = it.getString(R.string.happen_ftth_pop)))
                add(SingleChoiceModel(id = 13, account = it.getString(R.string.happen_ftth_error_onu)))
                add(SingleChoiceModel(id = 14, account = it.getString(R.string.happen_ftth_rain)))
            }
        }
        return list
    }

    fun getListHappenADSL(context: Context?): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            list.run {
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.happen_ftth_in_customer)))
                add(SingleChoiceModel(id = 2, account = it.getString(R.string.happen_adsl_wire_between)))
                add(SingleChoiceModel(id = 3, account = it.getString(R.string.happen_ftth_group_point)))
                add(SingleChoiceModel(id = 4, account = it.getString(R.string.happen_ftth_infrastructure)))
                add(SingleChoiceModel(id = 5, account = it.getString(R.string.happen_ftth_not_fix)))
                add(SingleChoiceModel(id = 6, account = it.getString(R.string.happen_ftth_system_error)))
                add(SingleChoiceModel(id = 7, account = it.getString(R.string.happen_ftth_other_reason)))
                add(SingleChoiceModel(id = 8, account = it.getString(R.string.happen_adsl_cancel_error)))
                add(SingleChoiceModel(id = 9, account = it.getString(R.string.happen_adsl_customer_appointment)))
                add(SingleChoiceModel(id = 12, account = it.getString(R.string.happen_ftth_pop)))
                add(SingleChoiceModel(id = 14, account = it.getString(R.string.happen_adsl_error_cable)))
                add(SingleChoiceModel(id = 16, account = it.getString(R.string.happen_adsl_error_infrastructure)))
                add(SingleChoiceModel(id = 18, account = it.getString(R.string.happen_adsl_burn_metal)))
                add(SingleChoiceModel(id = 19, account = it.getString(R.string.happen_adsl_cable_infrastructure)))
                add(SingleChoiceModel(id = 20, account = it.getString(R.string.happen_adsl_cable_underground)))
                add(SingleChoiceModel(id = 21, account = it.getString(R.string.happen_adsl_cable_control)))
                add(SingleChoiceModel(id = 22, account = it.getString(R.string.happen_adsl_standardize_database)))
                add(SingleChoiceModel(id = 23, account = it.getString(R.string.happen_ftth_rain)))
            }
        }
        return list
    }

    fun getListReasonType(context: Context?, type: Int): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            list.run {
                when (type) {
                    Constants.REASON_LIST_BY_ID_1 -> addDataReasonType1(it, this)
                    Constants.REASON_LIST_BY_ID_2 -> addDataReasonType2(it, this)
                    Constants.REASON_LIST_BY_ID_3 -> addDataReasonType3(it, this)
                    Constants.REASON_LIST_BY_ID_4 -> addDataReasonType4(it, this)
                    Constants.REASON_LIST_BY_ID_6 -> addDataReasonType6(it, this)
                    Constants.REASON_LIST_BY_ID_14 -> addDataReasonType14(it, this)
                    else -> add(SingleChoiceModel(id = 0, account = ""))
                }
            }
        }
        return list
    }

    private fun addDataReasonType1(context: Context?, arrayList: ArrayList<SingleChoiceModel>) {
        context?.let {
            arrayList.run {
                add(SingleChoiceModel(id = 40, account = it.getString(R.string.reason_breaking_wire_house)))
                add(SingleChoiceModel(id = 41, account = it.getString(R.string.reason_box_link)))
                add(SingleChoiceModel(id = 42, account = it.getString(R.string.reason_modem_error)))
                add(SingleChoiceModel(id = 43, account = it.getString(R.string.reason_equipment_error)))
                add(SingleChoiceModel(id = 44, account = it.getString(R.string.reason_lan_network)))
                add(SingleChoiceModel(id = 45, account = it.getString(R.string.reason_error_line)))
                add(SingleChoiceModel(id = 46, account = it.getString(R.string.reason_error_lan)))
                add(SingleChoiceModel(id = 47, account = it.getString(R.string.reason_break_indoor)))
                add(SingleChoiceModel(id = 48, account = it.getString(R.string.reason_voltage_weak)))
                add(SingleChoiceModel(id = 49, account = it.getString(R.string.reason_error_computer)))
                add(SingleChoiceModel(id = 50, account = it.getString(R.string.reason_concern)))
            }
        }
    }

    private fun addDataReasonType2(context: Context?, arrayList: ArrayList<SingleChoiceModel>) {
        context?.let {
            arrayList.run {
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.reason_breaking_electric)))
                add(SingleChoiceModel(id = 2, account = it.getString(R.string.reason_breaking_reason)))
            }
        }
    }

    private fun addDataReasonType3(context: Context?, arrayList: ArrayList<SingleChoiceModel>) {
        context?.let {
            arrayList.run {
                add(SingleChoiceModel(id = 35, account = it.getString(R.string.reason_jump_wire)))
                add(SingleChoiceModel(id = 36, account = it.getString(R.string.reason_coordinator)))
                add(SingleChoiceModel(id = 37, account = it.getString(R.string.reason_port_error)))
                add(SingleChoiceModel(id = 38, account = it.getString(R.string.reason_error_connect)))
            }
        }
    }

    private fun addDataReasonType4(context: Context?, arrayList: ArrayList<SingleChoiceModel>) {
        context?.let {
            arrayList.run {
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.reason_breaking_wire)))
                add(SingleChoiceModel(id = 2, account = it.getString(R.string.reason_pop_off)))
                add(SingleChoiceModel(id = 3, account = it.getString(R.string.reason_card_error)))
            }
        }
    }

    private fun addDataReasonType6(context: Context?, arrayList: ArrayList<SingleChoiceModel>) {
        context?.let {
            arrayList.run {
                add(SingleChoiceModel(id = 103, account = it.getString(R.string.reason_error_pop)))
                add(SingleChoiceModel(id = 104, account = it.getString(R.string.reason_full_traffic)))
                add(SingleChoiceModel(id = 105, account = it.getString(R.string.reason_error_metro)))
                add(SingleChoiceModel(id = 107, account = it.getString(R.string.reason_error_signal)))
            }
        }
    }

    private fun addDataReasonType14(context: Context?, arrayList: ArrayList<SingleChoiceModel>) {
        context?.let {
            arrayList.run {
                add(SingleChoiceModel(id = 30, account = it.getString(R.string.reason_breaking_outdoor)))
                add(SingleChoiceModel(id = 31, account = it.getString(R.string.reason_cable)))
                add(SingleChoiceModel(id = 32, account = it.getString(R.string.reason_error_open)))
            }
        }
    }

    fun getListReasonDescription(context: Context?, type: Int): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            list.run {
                when (type) {
                    Constants.REASON_DESCRIPTION_LIST_BY_ID_30 -> addDataReasonDescription30(it, this)
                    Constants.REASON_DESCRIPTION_LIST_BY_ID_37 -> addDataReasonDescription37(it, this)
                    Constants.REASON_DESCRIPTION_LIST_BY_ID_38 -> addDataReasonDescription38(it, this)
                    Constants.REASON_DESCRIPTION_LIST_BY_ID_42 -> addDataReasonDescription42(it, this)
                    Constants.REASON_DESCRIPTION_LIST_BY_ID_49 -> addDataReasonDescription49(it, this)
                    Constants.REASON_DESCRIPTION_LIST_BY_ID_103 -> addDataReasonDescription103(it, this)
                    Constants.REASON_DESCRIPTION_LIST_BY_ID_107 -> addDataReasonDescription107(it, this)
                    else -> add(SingleChoiceModel(id = 0, account = ""))
                }
            }
        }
        return list
    }

    private fun addDataReasonDescription30(context: Context?, arrayList: ArrayList<SingleChoiceModel>) {
        context?.let {
            arrayList.run {
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.reason_description_electricity)))
                add(SingleChoiceModel(id = 2, account = it.getString(R.string.reason_description_lower_underground)))
                add(SingleChoiceModel(id = 3, account = it.getString(R.string.reason_description_break_other)))
            }
        }
    }

    private fun addDataReasonDescription37(context: Context?, arrayList: ArrayList<SingleChoiceModel>) {
        context?.let {
            arrayList.run {
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.reason_description_port_cabenit)))
                add(SingleChoiceModel(id = 2, account = it.getString(R.string.reason_description_port_error)))
            }
        }
    }

    private fun addDataReasonDescription38(context: Context?, arrayList: ArrayList<SingleChoiceModel>) {
        context?.let {
            arrayList.run {
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.reason_description_wire)))
                add(SingleChoiceModel(id = 2, account = it.getString(R.string.reason_description_lost)))
            }
        }
    }

    private fun addDataReasonDescription42(context: Context?, arrayList: ArrayList<SingleChoiceModel>) {
        context?.let {
            arrayList.run {
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.reason_description_modem_die)))
                add(SingleChoiceModel(id = 2, account = it.getString(R.string.reason_description_modem_not)))
                add(SingleChoiceModel(id = 3, account = it.getString(R.string.reason_description_modem_hanged)))
                add(SingleChoiceModel(id = 4, account = it.getString(R.string.reason_description_modem_lose)))
                add(SingleChoiceModel(id = 5, account = it.getString(R.string.reason_description_modem_error)))
            }
        }
    }

    private fun addDataReasonDescription49(context: Context?, arrayList: ArrayList<SingleChoiceModel>) {
        context?.let {
            arrayList.run {
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.reason_description_hardware)))
                add(SingleChoiceModel(id = 2, account = it.getString(R.string.reason_description_software)))
            }
        }
    }

    private fun addDataReasonDescription103(context: Context?, arrayList: ArrayList<SingleChoiceModel>) {
        context?.let {
            arrayList.run {
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.reason_description_error_dslam)))
                add(SingleChoiceModel(id = 2, account = it.getString(R.string.reason_description_error_card)))
            }
        }
    }

    private fun addDataReasonDescription107(context: Context?, arrayList: ArrayList<SingleChoiceModel>) {
        context?.let {
            arrayList.run {
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.reason_description_error_lack)))
                add(SingleChoiceModel(id = 2, account = it.getString(R.string.reason_description_error_gate)))
            }
        }
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

    fun getListHiOpenNet(context: Context?): ArrayList<SingleChoiceModel> {
        val list = ArrayList<SingleChoiceModel>()
        context?.let {
            list.run {
                add(SingleChoiceModel(id = 1, account = it.getString(R.string.hiopennet_reason1)))
                add(SingleChoiceModel(id = 2, account = it.getString(R.string.hiopennet_reason2)))
                add(SingleChoiceModel(id = 3, account = it.getString(R.string.hiopennet_reason3)))
                add(SingleChoiceModel(id = 4, account = it.getString(R.string.hiopennet_reason4)))
                add(SingleChoiceModel(id = 5, account = it.getString(R.string.hiopennet_reason5)))
                add(SingleChoiceModel(id = 0, account = it.getString(R.string.hiopennet_reason0)))
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