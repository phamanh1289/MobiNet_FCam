package vn.com.fpt.mobinet_fcam.ui.utilities.connect_property.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_connect_property.view.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.DetailContractKeyValueModel
import vn.com.fpt.mobinet_fcam.ui.contract.detail.adpater.DetailContractDiff

/**
 * *******************************************
 * * Created by AnhPT76 on 28/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class ConnectPropertyAdapter : ListAdapter<DetailContractKeyValueModel, ConnectPropertyAdapter.ConnectPropertyHolder>(DetailContractDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConnectPropertyHolder {
        return ConnectPropertyHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_connect_property, parent, false))
    }

    override fun onBindViewHolder(holder: ConnectPropertyHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class ConnectPropertyHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(model: DetailContractKeyValueModel?) {
            model?.let { item ->
                itemView.itemConnectProperty_tvTitle.text = item.title
                itemView.itemConnectProperty_tvValue.text = item.value
                itemView.itemConnectProperty_llRootview.setBackgroundColor(ContextCompat.getColor(itemView.context, if (adapterPosition % 2 != 0) R.color.silver else R.color.white))
            }
        }
    }
}