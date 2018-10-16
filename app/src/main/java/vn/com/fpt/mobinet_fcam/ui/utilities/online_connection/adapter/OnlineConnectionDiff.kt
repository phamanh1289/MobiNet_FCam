package vn.com.fpt.mobinet_fcam.ui.contract.detail.adpater

import android.support.v7.util.DiffUtil
import vn.com.fpt.mobinet_fcam.data.network.model.KillSessionModel

/**
 * *******************************************
 * * Created by AnhPT76 on 28/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class OnlineConnectionDiff : DiffUtil.ItemCallback<KillSessionModel>() {
    override fun areItemsTheSame(oldItem: KillSessionModel, newItem: KillSessionModel): Boolean {
        return oldItem.ipaddress == newItem.ipaddress
    }

    override fun areContentsTheSame(oldItem: KillSessionModel, newItem: KillSessionModel): Boolean {
        return oldItem == newItem
    }

}