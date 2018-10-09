package vn.com.fpt.mobinet_fcam.ui.contract.list_result.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_employee.view.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.EmployeeModel

/**
 * *******************************************
 * * Created by AnhPT76 on 28/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class EmployeeAdapter(val onClick: (EmployeeModel) -> Unit) : ListAdapter<EmployeeModel, EmployeeAdapter.EmployeeHolder>(EmployeeDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeHolder {
        return EmployeeHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false))
    }

    override fun onBindViewHolder(holder: EmployeeHolder, position: Int) {
        holder.bindData(getItem(position), onClick)
    }

    inner class EmployeeHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(model: EmployeeModel?, onClick: (EmployeeModel) -> Unit) {
            model?.let { item ->
                itemView.itemEmployee_tvID.text = item.id
                itemView.itemEmployee_tvCode.text = item.codeemployee
                itemView.itemEmployee_tvName.text = item.name
                itemView.setOnClickListener { onClick(getItem(adapterPosition)) }
            }
        }
    }
}