package vn.com.fpt.mobinet_fcam.ui.contract.list_result.adapter

import android.support.v7.util.DiffUtil
import vn.com.fpt.mobinet_fcam.data.network.model.EmployeeModel

/**
 * *******************************************
 * * Created by AnhPT76 on 28/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class EmployeeDiff : DiffUtil.ItemCallback<EmployeeModel>() {
    override fun areItemsTheSame(oldItem: EmployeeModel, newItem: EmployeeModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EmployeeModel, newItem: EmployeeModel): Boolean {
        return oldItem == newItem
    }

}