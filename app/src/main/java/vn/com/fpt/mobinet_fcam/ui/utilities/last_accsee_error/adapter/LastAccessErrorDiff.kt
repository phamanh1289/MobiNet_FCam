package vn.com.fpt.mobinet_fcam.ui.utilities.last_accsee_error.adapter

import android.support.v7.util.DiffUtil
import vn.com.fpt.mobinet_fcam.data.network.model.LastAccessErrorModel

/**
 * *******************************************
 * * Created by AnhPT76 on 28/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class LastAccessErrorDiff : DiffUtil.ItemCallback<LastAccessErrorModel>() {
    override fun areItemsTheSame(oldItem: LastAccessErrorModel, newItem: LastAccessErrorModel): Boolean {
        return oldItem.errCode == newItem.errCode
    }

    override fun areContentsTheSame(oldItem: LastAccessErrorModel, newItem: LastAccessErrorModel): Boolean {
        return oldItem == newItem
    }

}