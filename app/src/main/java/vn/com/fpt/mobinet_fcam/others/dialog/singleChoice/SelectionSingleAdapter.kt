package vn.com.fpt.mobinet_fcam.others.dialog.singleChoice

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_selection_single.view.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.SingleChoiceModel

/**
 * *******************************************
 * * Created by AnhPT76 on 01/10/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class SelectionSingleAdapter(val onClick: (Int) -> Unit) : ListAdapter<SingleChoiceModel, SelectionSingleAdapter.SelectionSingleHolder>(SelectionSingleDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectionSingleHolder {
        return SelectionSingleHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_selection_single, parent, false))
    }

    override fun onBindViewHolder(holder: SelectionSingleHolder, position: Int) {
        holder.bindData(getItem(position), onClick)
    }

    inner class SelectionSingleHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(item: SingleChoiceModel, onClick: (Int) -> Unit) {
            itemView.itemSelection_tvTitle.text = item.account.trim()
            item.status.run {
                itemView.itemSelection_rbCheck.isSelected = this
                itemView.itemSelection_tvTitle.isSelected = this
            }
            itemView.setOnClickListener {
                onClick(adapterPosition)
            }
        }
    }
}