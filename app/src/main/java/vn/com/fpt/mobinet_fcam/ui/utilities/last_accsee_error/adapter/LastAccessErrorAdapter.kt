package vn.com.fpt.mobinet_fcam.ui.utilities.last_accsee_error.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_last_access_error.view.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.LastAccessErrorModel

/**
 * *******************************************
 * * Created by AnhPT76 on 28/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class LastAccessErrorAdapter : ListAdapter<LastAccessErrorModel, LastAccessErrorAdapter.LastAccessErrorHolder>(LastAccessErrorDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastAccessErrorHolder {
        return LastAccessErrorHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_last_access_error, parent, false))
    }

    override fun onBindViewHolder(holder: LastAccessErrorHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class LastAccessErrorHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(model: LastAccessErrorModel?) {
            model?.let { item ->
                itemView.itemAccessError_tvDescriptionValue.text = item.errDescription
                itemView.itemAccessError_tvCodeValue.text = item.errCode
                itemView.itemAccessError_tvDateValue.text = item.errDate
                itemView.itemAccessError_tvCallerIdValue.text = item.errCallerId
                itemView.itemAccessError_tvAdditionalValue.text = item.errAdditional
            }
        }
    }
}