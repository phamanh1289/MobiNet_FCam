package vn.com.fpt.mobinet_fcam.others.dialog.singleChoice

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import kotlinx.android.synthetic.main.fragment_dialog_single_choice.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.SingleChoiceModel
import vn.com.fpt.mobinet_fcam.utils.AppUtils

/**
 * *******************************************
 * * Created by AnhPT76 on 01/10/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class SingChoiceDialog : DialogFragment() {

    private var title = ""
    private var index = 0
    private var list = ArrayList<SingleChoiceModel>()
    lateinit var singleAdapter: SelectionSingleAdapter
    private lateinit var onClick: (Int) -> Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.fragment_dialog_single_choice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDataDialog()
    }

    private fun loadDataDialog() {
        singleAdapter = SelectionSingleAdapter(onClick)
        submitData(list)
        fragDialogSingleChoice_rvMain.apply {
            val layout = LinearLayoutManager(context)
            layout.orientation = LinearLayoutManager.VERTICAL
            layoutManager = layout
            adapter = singleAdapter
            setHasFixedSize(true)
            scrollToPosition(index)
        }
        fragDialogSingleChoice_tvTitle.text = title
    }

    fun submitData(list: ArrayList<SingleChoiceModel>) {
        singleAdapter.submitList(list)
    }

    fun setDataDialog(title: String, list: ArrayList<SingleChoiceModel>, index: Int, onClick: (Int) -> Unit) {
        this.title = title
        this.list = list
        this.onClick = onClick
        this.index = index
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(AppUtils.getScreenWidth() - resources.getDimension(R.dimen._70dp).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}