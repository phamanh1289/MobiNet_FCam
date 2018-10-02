package vn.com.fpt.mobinet_fcam.ui.contract.list_result.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_result_deployment.view.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.InfoContractModel

/**
 * *******************************************
 * * Created by AnhPT76 on 28/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class ListMaintenanceAdapter(val onClick: (Int) -> Unit) : ListAdapter<InfoContractModel, ListMaintenanceAdapter.ListResultHolder>(ListResultDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListResultHolder {
        return ListResultHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_result_maintenance, parent, false))
    }

    override fun onBindViewHolder(holder: ListResultHolder, position: Int) {
        holder.bindData(getItem(position), onClick)
    }

    inner class ListResultHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(model: InfoContractModel?, onClick: (Int) -> Unit) {
            model?.let { item ->
                itemView.itemResultDeployment_tvContract.text = item.contract
//                itemView.itemResultDeployment_tvCreateDate.text = AppUtils.toConvertTimeToString(itemView.context, item.datecreate.ifNullOrEmpty(""))
//                    itemView.itemResultDeployment_tvAssignDate.text = AppUtils.toConvertTimeToString(itemView.context, item.dateassign.ifNullOrEmpty(""))
                itemView.itemResultDeployment_tvServiceType.text = item.typecus
                itemView.itemResultDeployment_tvPriority.text = item.priority
                itemView.setOnClickListener { onClick(adapterPosition) }
            }
        }
    }
}