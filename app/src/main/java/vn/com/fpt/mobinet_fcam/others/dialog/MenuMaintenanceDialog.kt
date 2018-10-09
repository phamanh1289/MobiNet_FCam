package vn.com.fpt.mobinet_fcam.others.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import kotlinx.android.synthetic.main.fragment_dialog_menu_maintenance.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.interfaces.MenuMaintenanceDialogInterface
import vn.com.fpt.mobinet_fcam.utils.AppUtils

/**
 * *******************************************
 * * Created by AnhPT76 on 09/10/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class MenuMaintenanceDialog : BottomSheetDialogFragment() {

    private var menuListener: MenuMaintenanceDialogInterface? = null
    private var isCheck = false
    private var title = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)
        return inflater.inflate(R.layout.fragment_dialog_menu_maintenance, container, false)
    }

    fun setListener(listener: MenuMaintenanceDialogInterface, isCheck: Boolean, title: String) {
        this.menuListener = listener
        this.isCheck = isCheck
        this.title = title
    }

    private fun handleMemberOfTeam() {
        val status = if (isCheck) View.VISIBLE else View.GONE
        fragDialogMenuMaintenance_tvAssign.visibility = status
        fragDialogMenuMaintenance_viewAssign.visibility = status
        fragDialogMenuMaintenance_tvTitle.text = title
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.SheetDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClick()
        handleMemberOfTeam()
    }

    private fun initOnClick() {
        fragDialogMenuMaintenance_tvAssign.setOnClickListener {
            menuListener?.actionAssign()
            dismiss()
        }
        fragDialogMenuMaintenance_tvAccept.setOnClickListener {
            menuListener?.actionAccept()
            dismiss()
        }
        fragDialogMenuMaintenance_tvNext.setOnClickListener {
            menuListener?.actionNext()
            dismiss()
        }
        fragDialogMenuMaintenance_tvCancel.setOnClickListener { dismiss() }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(AppUtils.getScreenWidth() - resources.getDimension(R.dimen._40dp).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}