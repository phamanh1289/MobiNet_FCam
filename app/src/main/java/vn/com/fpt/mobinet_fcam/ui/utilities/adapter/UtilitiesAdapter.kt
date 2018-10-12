package vn.com.fpt.mobinet_fcam.ui.utilities.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_utilities.view.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.MenuModel
import vn.com.fpt.mobinet_fcam.ui.functions.adapter.FunctionsDiff

/**
 * *******************************************
 * * Created by AnhPT76 on 28/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class UtilitiesAdapter(val onClick: (Int) -> Unit) : ListAdapter<MenuModel, UtilitiesAdapter.UtilitiesHolder>(FunctionsDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UtilitiesHolder {
        return UtilitiesHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_utilities, parent, false))
    }

    override fun onBindViewHolder(holder: UtilitiesHolder, position: Int) {
        holder.bindData(getItem(position), onClick)
    }

    inner class UtilitiesHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(model: MenuModel?, onClick: (Int) -> Unit) {
            model?.let { item ->
                itemView.itemUtilities_textTitle.text = item.title
                Glide.with(itemView.context).load(item.image).into(itemView.itemUtilities_imgView)
                itemView.setOnClickListener { onClick(adapterPosition) }
            }
        }
    }
}