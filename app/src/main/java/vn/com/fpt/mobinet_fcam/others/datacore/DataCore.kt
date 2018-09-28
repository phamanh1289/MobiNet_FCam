package vn.com.fpt.mobinet_fcam.others.datacore

import android.content.Context
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.MenuModel

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
            list.add(MenuModel(title = it.getString(R.string.menu_deployment), image = R.drawable.ic_menu_deployment))
            list.add(MenuModel(title = it.getString(R.string.menu_maintenance), image = R.drawable.ic_menu_maintance))
            list.add(MenuModel(title = it.getString(R.string.menu_utilities), image = R.drawable.ic_menu_utilities))
            list.add(MenuModel(title = it.getString(R.string.menu_port_net), image = R.drawable.ic_menu_port_net))
            list.add(MenuModel(title = it.getString(R.string.menu_report), image = R.drawable.ic_menu_report))
            list.add(MenuModel(title = it.getString(R.string.menu_info), image = R.drawable.ic_menu_info))
        }
        return list
    }
}