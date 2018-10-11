package vn.com.fpt.mobinet_fcam.others.dialog.hiOpenNet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import kotlinx.android.synthetic.main.fragment_dialog_hi_open_net.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.SingleChoiceModel
import vn.com.fpt.mobinet_fcam.utils.AppUtils

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class HiOpenNetDialog : DialogFragment() {

    private var list = ArrayList<SingleChoiceModel>()
    lateinit var singleAdapter: HiOpenNetAdapter
    private lateinit var onClick: (Int) -> Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)
        isCancelable = false
        return inflater.inflate(R.layout.fragment_dialog_hi_open_net, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDataDialog()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(AppUtils.getScreenWidth() - resources.getDimension(R.dimen._40dp).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun loadDataDialog() {
        singleAdapter = HiOpenNetAdapter {
            val item = list[singleAdapter.getSelectIndex()]
            item.status = false
            singleAdapter.notifyItemChanged(singleAdapter.getSelectIndex())
            val currentItem = list[it]
            currentItem.status = true
            singleAdapter.notifyItemChanged(it)
        }
        singleAdapter.submitList(list)
        fragDialogHiOpenNet_rvMain.apply {
            val layout = LinearLayoutManager(context)
            layout.orientation = LinearLayoutManager.VERTICAL
            layoutManager = layout
            adapter = singleAdapter
            setHasFixedSize(true)
        }
        fragDialogHiOpenNet_tvCancel.setOnClickListener {
            dismiss()
        }
        fragDialogHiOpenNet_tvOK.setOnClickListener {
            onClick(singleAdapter.getSelectIndex())
            dismiss()
        }
    }

    fun setDataDialog(list: ArrayList<SingleChoiceModel>, onClick: (Int) -> Unit) {
        this.list = list
        this.onClick = onClick
    }

}