package vn.com.fpt.mobinet_fcam.ui.contract.update.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.item_update_contract.view.*
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
class UpdateContractAdapter : ListAdapter<DetailContractKeyValueModel, UpdateContractAdapter.DetailContractHolder>(DetailContractDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailContractHolder {
        return DetailContractHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_update_contract, parent, false))
    }

    override fun onBindViewHolder(holder: DetailContractHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class DetailContractHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(model: DetailContractKeyValueModel?) {
            model?.let { item ->
                itemView.itemUpdateContract_tvValue.onChange()
                itemView.itemUpdateContract_tvTitle.text = item.title
                itemView.itemUpdateContract_tvValue.setText(item.value)
            }
        }

        private fun EditText?.onChange() {
            this?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    getItem(adapterPosition).value = s.toString()
                }
            })
        }
    }
}