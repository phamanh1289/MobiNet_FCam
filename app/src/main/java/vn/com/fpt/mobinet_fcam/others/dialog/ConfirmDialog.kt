package vn.com.fpt.mobinet_fcam.others.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import kotlinx.android.synthetic.main.fragment_dialog_confirm.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.interfaces.ConfirmDialogInterface
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.utils.AppUtils

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class ConfirmDialog : DialogFragment() {

    private var titleDialog = ""
    private var contentDialog = ""
    private var actionCancel = false
    private var confirmDialogInterface: ConfirmDialogInterface? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)
        isCancelable = false
        return inflater.inflate(R.layout.fragment_dialog_confirm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleUIDialog()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(AppUtils.getScreenWidth() - resources.getDimension(R.dimen._40dp).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun setDataDialog(title: String = "", content: String = "", actionCancel: Boolean = false, confirmDialogInterface: ConfirmDialogInterface?) {
        this.titleDialog = title
        this.contentDialog = content
        this.actionCancel = actionCancel
        this.confirmDialogInterface = confirmDialogInterface
    }

    private fun handleUIDialog() {
        fragDialogConfirm_tvTitle.text = if (titleDialog.isNotBlank()) titleDialog else Constants.TITLE_DIALOG
        fragDialogConfirm_tvContent.text = contentDialog
        if (actionCancel) {
            fragDialogConfirm_vLineHorizontal.visibility = View.VISIBLE
            fragDialogConfirm_tvCancel.visibility = View.VISIBLE
            fragDialogConfirm_tvCancel.setOnClickListener {
                confirmDialogInterface?.onClickCancel()
                dismiss()
            }
        }
        fragDialogConfirm_tvOK.setOnClickListener {
            confirmDialogInterface?.onClickOk()
            dismiss()
        }
    }
}