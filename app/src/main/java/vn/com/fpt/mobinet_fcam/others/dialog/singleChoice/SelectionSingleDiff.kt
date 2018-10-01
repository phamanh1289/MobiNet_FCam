package vn.com.fpt.mobinet_fcam.others.dialog.singleChoice

import android.support.v7.util.DiffUtil
import vn.com.fpt.mobinet_fcam.data.network.model.SingleChoiceModel

/**
 * *******************************************
 * * Created by AnhPT76 on 01/10/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class SelectionSingleDiff : DiffUtil.ItemCallback<SingleChoiceModel>() {
    override fun areItemsTheSame(oldItem: SingleChoiceModel, newItem: SingleChoiceModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SingleChoiceModel, newItem: SingleChoiceModel): Boolean {
        return oldItem == newItem
    }

}