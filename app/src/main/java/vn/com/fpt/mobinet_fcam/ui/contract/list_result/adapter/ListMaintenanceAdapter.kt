package vn.com.fpt.mobinet_fcam.ui.contract.list_result.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_result_maintenance.view.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.InfoContractModel
import vn.com.fpt.mobinet_fcam.utils.addTimeToDate
import vn.com.fpt.mobinet_fcam.utils.convertToFullFormat

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
                itemView.itemResultMaintenance_tvContract.text = item.contract
                itemView.itemResultMaintenance_tvCreateDate.text = item.datecreate.convertToFullFormat("")
                itemView.itemResultMaintenance_tvAssignDate.text = item.appointmentdate.addTimeToDate("")
                itemView.itemResultMaintenance_tvPriority.text = item.priority
                itemView.itemResultMaintenance_tvRemainHour.text = item.hourremain
                itemView.itemResultMaintenance_tvCustomer.text = item.fullname
                itemView.itemResultMaintenance_tvAddress.text = item.address
                itemView.itemResultMaintenance_tvTotalChecklist.text = item.totalchecklist.toString()
                itemView.itemResultMaintenance_tvTotalInMonth.text = item.totalchecklistinmonth.toString()
                itemView.setOnClickListener { onClick(adapterPosition) }
            }
        }
    }
}