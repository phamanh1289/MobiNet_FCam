package vn.com.fpt.mobinet_fcam.ui.contract.search_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_search_list.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.SearchListContractModel
import vn.com.fpt.mobinet_fcam.data.network.model.SingleChoiceModel
import vn.com.fpt.mobinet_fcam.data.network.model.TitleAndMenuModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.others.datacore.DataCore
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
import vn.com.fpt.mobinet_fcam.ui.contract.result.ResultFragment
import vn.com.fpt.mobinet_fcam.utils.AppUtils
import vn.com.fpt.mobinet_fcam.utils.KeyboardUtils
import javax.inject.Inject

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class SearchListFragment : BaseFragment(), SearchListContract.SearchListView {
    @Inject
    lateinit var presenter: SearchListPresenter

    private var typeContract = Constants.CONTRACT_DEPLOYMENT
    private lateinit var listServiceType: ArrayList<SingleChoiceModel>
    private lateinit var listCheckType: ArrayList<SingleChoiceModel>
    private var positionServiceType = 0
    private var positionCheckType = 0

    companion object {
        fun newInstance(typeContract: String): SearchListFragment {
            val args = Bundle()
            args.putString(Constants.TYPE_CONTRACT, typeContract)
            val fragment = SearchListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {
        handleArgument()
        getDefaultData()
        initOnClick()
    }

    private fun initOnClick() {
        fragSearchList_tvServiceType.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager, getString(R.string.search_list_serviceType), listServiceType, fragSearchList_tvServiceType, positionServiceType) }
        fragSearchList_tvFromDate.setOnClickListener { AppUtils.showPickTime(context, fragSearchList_tvFromDate, Constants.SET_CURRENT_IS_MAX_DATE) }
        fragSearchList_tvToDate.setOnClickListener { AppUtils.showPickTime(context, fragSearchList_tvToDate, Constants.SET_CURRENT_IS_MAX_DATE) }
        fragSearchList_tvView.setOnClickListener { initParams() }
        fragSearchList_tvCancel.setOnClickListener { activity?.onBackPressed() }
    }

    private fun initParams() {
        val result = AppUtils.handleCheckDate(context, fragSearchList_tvFromDate.text.toString(), fragSearchList_tvToDate.text.toString())
        if (result.isBlank())
            presenter.let {
                showLoading()
                val map = HashMap<String, Any>()
                map[Constants.PARAM_USER_NAME_UPPER] = getDefaultUser()?.mobiaccount.toString()
                map[Constants.PARAM_FROM_DATE_UPPER] = AppUtils.toConvertDateFormat(context, fragSearchList_tvFromDate.text.toString())
                map[Constants.PARAM_TO_DATE] = AppUtils.toConvertDateFormat(context, fragSearchList_tvToDate.text.toString())
                map[Constants.PARAM_TYPE_UPPER] = listServiceType[positionServiceType].id
                if (typeContract == Constants.CONTRACT_MAINTENANCE) {
                    map[Constants.PARAM_CHECK_LIST_TYPE_UPPER] = listCheckType[positionCheckType].id
                    it.getContractMaintenance(map)
                } else
                    it.getContractDeployment(map)
            }
        else AppUtils.showDialog(fragmentManager, content = result, confirmDialogInterface = null)
    }

    private fun getDefaultData() {
        listServiceType = DataCore.getListTypeContract(context)
        listServiceType[Constants.FIRST_ITEM].status = true
        fragSearchList_tvServiceType.text = listServiceType[Constants.FIRST_ITEM].account
        if (typeContract != Constants.CONTRACT_DEPLOYMENT) {
            listCheckType = DataCore.getListCheckType(context)
            listCheckType[Constants.FIRST_ITEM].status = true
            fragSearchList_tvCheckList.text = listCheckType[Constants.FIRST_ITEM].account
            fragSearchList_tvCheckList.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager, getString(R.string.search_list_checkListType), listCheckType, fragSearchList_tvCheckList, positionCheckType) }
        }
        AppUtils.getDefaultDateSearch(fragSearchList_tvToDate, fragSearchList_tvFromDate, Constants.LATE_DATE)
    }

    fun setDefaultValueIndex(view: Int, index: Int) {
        when (view) {
            R.id.fragSearchList_tvCheckList -> {
                positionCheckType = index
            }
            R.id.fragSearchList_tvServiceType -> {
                positionServiceType = index
            }
        }
    }

    private fun handleArgument() {
        arguments?.let {
            typeContract = it.getString(Constants.TYPE_CONTRACT)
        }
        fragSearchList_llCheckList.visibility = if (typeContract == Constants.CONTRACT_DEPLOYMENT) View.GONE else View.VISIBLE
        setTitle(TitleAndMenuModel(title = getString(if (typeContract == Constants.CONTRACT_DEPLOYMENT) R.string.title_search_list_deployment else R.string.title_search_list_maintenance)))
    }

    override fun loadContractDeployment(response: SearchListContractModel) {
        hideLoading()
        addFragment(ResultFragment.newInstance(response, typeContract), true, true)
    }

    override fun handleError(response: String) {
        hideLoading()
        AppUtils.showDialog(fragmentManager, title = getString(R.string.mess_error_data), content = response, confirmDialogInterface = null)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}