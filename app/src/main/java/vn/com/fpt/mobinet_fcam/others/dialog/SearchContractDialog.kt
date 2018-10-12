package vn.com.fpt.mobinet_fcam.others.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import kotlinx.android.synthetic.main.fragment_dialog_search_contract.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.utils.AppUtils

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class SearchContractDialog : DialogFragment() {

    private lateinit var onClick: (String, String) -> Unit
    private var typeSearch = ""
    private var isCheckTypeSearch = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)
        isCancelable = false
        return inflater.inflate(R.layout.fragment_dialog_search_contract, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleUIDialog()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(AppUtils.getScreenWidth() - resources.getDimension(R.dimen._40dp).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun setDataDialog(onClick: (String, String) -> Unit) {
        this.onClick = onClick
    }

    private fun handleUIDialog() {
        handleTypeSearch()
        fragDialogSearchContract_imgContract.setOnClickListener { handleTypeSearch() }
        fragDialogSearchContract_tvContract.setOnClickListener { handleTypeSearch() }
        fragDialogSearchContract_imgUserName.setOnClickListener { handleTypeSearch() }
        fragDialogSearchContract_tvUserName.setOnClickListener { handleTypeSearch() }
        fragDialogSearchContract_tvCancel.setOnClickListener {
            dismiss()
        }
        fragDialogSearchContract_tvSearch.setOnClickListener {
            onClick(fragDialogSearchContract_etContentSearch.text.toString(), typeSearch)
        }
    }

    private fun handleTypeSearch() {
        fragDialogSearchContract_imgContract.isSelected = isCheckTypeSearch
        fragDialogSearchContract_tvContract.isSelected = isCheckTypeSearch
        typeSearch = if (isCheckTypeSearch) Constants.CONTRACT else Constants.USER_NAME
        isCheckTypeSearch = !isCheckTypeSearch
        fragDialogSearchContract_imgUserName.isSelected = isCheckTypeSearch
        fragDialogSearchContract_tvUserName.isSelected = isCheckTypeSearch
    }
}