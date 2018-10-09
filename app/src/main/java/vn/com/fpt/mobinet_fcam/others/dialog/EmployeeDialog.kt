package vn.com.fpt.mobinet_fcam.others.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import kotlinx.android.synthetic.main.fragment_dialog_deployee.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.EmployeeModel
import vn.com.fpt.mobinet_fcam.ui.contract.list_result.adapter.EmployeeAdapter
import vn.com.fpt.mobinet_fcam.utils.AppUtils

/**
 * *******************************************
 * * Created by AnhPT76 on 09/10/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class EmployeeDialog : DialogFragment() {

    private var title = ""
    private lateinit var adapterEmployeeAdapter: EmployeeAdapter
    private var listEmployee = ArrayList<EmployeeModel>()
    private lateinit var onClickDialog: (EmployeeModel) -> Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.fragment_dialog_deployee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleUiDialog()
    }

    private fun handleUiDialog() {
        fragDialogEmployee_tvTitle.text = title
        adapterEmployeeAdapter = EmployeeAdapter(onClickDialog)
        adapterEmployeeAdapter.apply {
            submitList(listEmployee)
            notifyDataSetChanged()
        }
        fragDialogEmployee_rvMain.apply {
            val layout = LinearLayoutManager(context)
            layout.orientation = LinearLayoutManager.VERTICAL
            layoutManager = layout
            adapter = adapterEmployeeAdapter
            setHasFixedSize(true)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(AppUtils.getScreenWidth() - resources.getDimension(R.dimen._40dp).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun setDataDialog(title: String = "", listEmployee: ArrayList<EmployeeModel>, onClick: (EmployeeModel) -> Unit) {
        this.title = title
        this.listEmployee = listEmployee
        this.onClickDialog = onClick
    }
}