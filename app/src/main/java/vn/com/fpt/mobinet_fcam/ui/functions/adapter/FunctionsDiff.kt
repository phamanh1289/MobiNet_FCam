package vn.com.fpt.mobinet_fcam.ui.functions.adapter

import android.support.v7.util.DiffUtil
import vn.com.fpt.mobinet_fcam.data.network.model.MenuModel

/**
 * *******************************************
 * * Created by AnhPT76 on 28/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class FunctionsDiff : DiffUtil.ItemCallback<MenuModel>() {
    override fun areItemsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean {
        return oldItem == newItem
    }

}