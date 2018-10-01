package vn.com.fpt.mobinet_fcam.ui.contract.list_result.adapter

import android.support.v7.util.DiffUtil
import vn.com.fpt.mobinet_fcam.data.network.model.InfoContractModel

/**
 * *******************************************
 * * Created by AnhPT76 on 28/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class ListResultDiff : DiffUtil.ItemCallback<InfoContractModel>() {
    override fun areItemsTheSame(oldItem: InfoContractModel, newItem: InfoContractModel): Boolean {
        return oldItem.objid == newItem.objid
    }

    override fun areContentsTheSame(oldItem: InfoContractModel, newItem: InfoContractModel): Boolean {
        return oldItem == newItem
    }

}