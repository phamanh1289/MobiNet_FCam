package vn.com.fpt.mobinet_fcam.ui.contract.detail.adpater

import android.support.v7.util.DiffUtil
import vn.com.fpt.mobinet_fcam.data.network.model.DetailContractKeyValueModel

/**
 * *******************************************
 * * Created by AnhPT76 on 28/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class DetailContractDiff : DiffUtil.ItemCallback<DetailContractKeyValueModel>() {
    override fun areItemsTheSame(oldItem: DetailContractKeyValueModel, newItem: DetailContractKeyValueModel): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: DetailContractKeyValueModel, newItem: DetailContractKeyValueModel): Boolean {
        return oldItem == newItem
    }

}