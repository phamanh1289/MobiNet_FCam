package vn.com.fpt.mobinet_fcam.ui.functions

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_function.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.MenuModel
import vn.com.fpt.mobinet_fcam.data.network.model.SearchContractModel
import vn.com.fpt.mobinet_fcam.data.network.model.TitleAndMenuModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.others.datacore.DataCore
import vn.com.fpt.mobinet_fcam.others.dialog.SearchContractDialog
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
import vn.com.fpt.mobinet_fcam.ui.contract.report.ReportContractFragment
import vn.com.fpt.mobinet_fcam.ui.contract.search_list.SearchListFragment
import vn.com.fpt.mobinet_fcam.ui.utilities.UtilitiesFragment
import vn.com.fpt.mobinet_fcam.ui.functions.adapter.FunctionsAdapter
import vn.com.fpt.mobinet_fcam.ui.info.InfoFragment
import vn.com.fpt.mobinet_fcam.ui.port_net.PortNetFragment
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
class FunctionsFragment : BaseFragment(), FunctionsContract.FunctionsView {
    @Inject
    lateinit var presenter: FunctionsPresenter

    private lateinit var adapterMenu: FunctionsAdapter
    private var listMenu = ArrayList<MenuModel>()
    private var dialogSearch: SearchContractDialog? = null

    companion object {
        const val MAX_COL = 2
        fun newInstance(supId: String): FunctionsFragment {
            val args = Bundle()
            args.putString("", supId)
            val fragment = FunctionsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_function, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {
        setTitle(TitleAndMenuModel(title = getString(R.string.functions)))
        listMenu = DataCore.getListMenu(context)
        adapterMenu = FunctionsAdapter { handleOnClick(it) }
        adapterMenu.submitList(listMenu)
        fragFunction_rvMain?.apply {
            adapter = adapterMenu
            val layout = GridLayoutManager(context, MAX_COL)
            layoutManager = layout
            setHasFixedSize(true)
        }
        adapterMenu.notifyDataSetChanged()
    }

    private fun handleOnClick(position: Int) {
        if (position == Constants.MENU_UTILITIES)
            showDialogSearchContract()
        else
            addFragment(when (position) {
                Constants.MENU_DEPLOYMENT_LIST -> SearchListFragment.newInstance(typeContract = Constants.CONTRACT_DEPLOYMENT)
                Constants.MENU_MAINTENANCE_LIST -> SearchListFragment.newInstance(typeContract = Constants.CONTRACT_MAINTENANCE)
                Constants.MENU_UTILITIES -> UtilitiesFragment()
                Constants.MENU_PORT_NET -> PortNetFragment()
                Constants.MENU_REPORT -> ReportContractFragment()
                else -> InfoFragment() //Else : MENU_INFO
            }, true, true)
    }

    private fun showDialogSearchContract() {
        fragmentManager?.let {
            if (dialogSearch != null)
                dialogSearch = null
            dialogSearch = SearchContractDialog()
            dialogSearch?.setDataDialog { data, type ->
                showLoading()
                presenter.postSearchContract(data, type)
            }
            if (!it.isStateSaved)
                dialogSearch?.show(it, SearchContractDialog::class.java.simpleName)
        }
    }

    override fun loadSearchContract(response: SearchContractModel) {
        hideLoading()
        if (response.objid != 0) {
            dialogSearch?.dismiss()
            addFragment(UtilitiesFragment.newInstance(response), true, true)
        } else AppUtils.showDialog(fragmentManager, content = getString(R.string.not_found_contract), confirmDialogInterface = null)
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