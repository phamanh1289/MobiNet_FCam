package vn.com.fpt.mobinet_fcam.ui.utilities.online_connection.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_online_connection.view.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.KillSessionModel
import vn.com.fpt.mobinet_fcam.ui.contract.detail.adpater.OnlineConnectionDiff

/**
 * *******************************************
 * * Created by AnhPT76 on 28/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class OnlineConnectionAdapter : ListAdapter<KillSessionModel, OnlineConnectionAdapter.OnlineConnectionHolder>(OnlineConnectionDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnlineConnectionHolder {
        return OnlineConnectionHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_online_connection, parent, false))
    }

    override fun onBindViewHolder(holder: OnlineConnectionHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class OnlineConnectionHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(model: KillSessionModel?) {
            model?.let { item ->
                itemView.itemOnlineConnection_tvIpAddressValue.text = item.ipaddress
                itemView.itemOnlineConnection_tvNasNameValue.text = item.nasname
                itemView.itemOnlineConnection_tvDateValue.text = item.date
                itemView.itemOnlineConnection_tvCallerIdValue.text = item.callerid
            }
        }
    }
}