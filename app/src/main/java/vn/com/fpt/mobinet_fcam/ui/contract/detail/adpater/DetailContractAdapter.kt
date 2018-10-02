package vn.com.fpt.mobinet_fcam.ui.contract.detail.adpater

import android.support.v4.content.ContextCompat
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_detail_contract.view.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.DetailContractKeyValueModel

/**
 * *******************************************
 * * Created by AnhPT76 on 28/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class DetailContractAdapter(val onClick: (Int) -> Unit) : ListAdapter<DetailContractKeyValueModel, DetailContractAdapter.DetailContractHolder>(DetailContractDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailContractHolder {
        return DetailContractHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_detail_contract, parent, false))
    }

    override fun onBindViewHolder(holder: DetailContractHolder, position: Int) {
        holder.bindData(getItem(position), onClick)
    }

    inner class DetailContractHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(model: DetailContractKeyValueModel?, onClick: (Int) -> Unit) {
            model?.let { item ->
                itemView.itemDetailContract_tvTitle.text = item.title
                itemView.itemDetailContract_tvValue.text = item.value
                if (adapterPosition % 2 != 0)
                    itemView.itemDetailContract_llRootView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.silver))
                val mColor = when (item.key) {
                    itemView.context.getString(R.string.key_detail_contract_image) -> R.color.light_blue_300
                    itemView.context.getString(R.string.item_result_contract) -> R.color.redMain
                    else -> R.color.blueMain
                }
                itemView.itemDetailContract_tvValue.setTextColor(ContextCompat.getColor(itemView.context, mColor))
                if (mColor == R.color.light_blue_300)
                    itemView.itemDetailContract_tvValue.setOnClickListener { onClick(adapterPosition) }
            }
        }
    }
}