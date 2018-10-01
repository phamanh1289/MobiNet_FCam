package vn.com.fpt.mobinet_fcam.ui.functions.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_menu_main.view.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.MenuModel

/**
 * *******************************************
 * * Created by AnhPT76 on 28/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class FunctionsAdapter (val onClick: (Int) -> Unit) : ListAdapter<MenuModel, FunctionsAdapter.FunctionsHolder>(FunctionsDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunctionsHolder {
        return FunctionsHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_menu_main, parent, false))
    }

    override fun onBindViewHolder(holder: FunctionsHolder, position: Int) {
        holder.bindData(getItem(position), onClick)
    }

    inner class FunctionsHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(model: MenuModel?, onClick: (Int) -> Unit) {
            model?.let { item ->
                itemView.itemMenuMain_textTitle.text = item.title
                itemView.itemMenuMain_imgView.setImageResource(item.image)
                itemView.setOnClickListener { onClick(adapterPosition) }
            }
        }
    }
}