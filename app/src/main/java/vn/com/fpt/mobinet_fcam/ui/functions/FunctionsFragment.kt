package vn.com.fpt.mobinet_fcam.ui.functions

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_function.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.MenuModel
import vn.com.fpt.mobinet_fcam.data.network.model.TitleAndMenuModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.others.datacore.DataCore
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
import vn.com.fpt.mobinet_fcam.ui.contract.report.ReportContractFragment
import vn.com.fpt.mobinet_fcam.ui.contract.search_list.SearchListFragment
import vn.com.fpt.mobinet_fcam.ui.contract.utilities.SearchContractFragment
import vn.com.fpt.mobinet_fcam.ui.functions.adapter.FunctionsAdapter
import vn.com.fpt.mobinet_fcam.ui.info.InfoFragment
import vn.com.fpt.mobinet_fcam.ui.login.BlankPresenter
import vn.com.fpt.mobinet_fcam.ui.port_net.PortNetFragment
import vn.com.fpt.mobinet_fcam.utils.KeyboardUtils
import javax.inject.Inject

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class FunctionsFragment : BaseFragment() {
    @Inject
    lateinit var presenter: BlankPresenter

    private lateinit var adapterMenu: FunctionsAdapter
    private var listMenu = ArrayList<MenuModel>()

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
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {
        setTitle(TitleAndMenuModel(title = getString(R.string.functions)))
        listMenu = DataCore.getListMenu(context)
        adapterMenu = FunctionsAdapter { handleOnClick(it) }
        adapterMenu.submitList(listMenu)
        fragFunction_rvMain.apply {
            adapter = adapterMenu
            val layout = GridLayoutManager(context, MAX_COL)
            layoutManager = layout
            setHasFixedSize(true)
        }
        adapterMenu.notifyDataSetChanged()
    }

    private fun handleOnClick(position: Int) {
        val fragment = when (position) {
            Constants.MENU_DEPLOYMENT_LIST -> SearchListFragment.newInstance(typeContract = Constants.CONTRACT_DEPLOYMENT)
            Constants.MENU_MAINTENANCE_LIST -> SearchListFragment.newInstance(typeContract = Constants.CONTRACT_MAINTENANCE)
            Constants.MENU_UTILITIES -> SearchContractFragment()
            Constants.MENU_PORT_NET -> PortNetFragment()
            Constants.MENU_REPORT -> ReportContractFragment()
            else -> InfoFragment() //Else : MENU_INFO
        }
        addFragment(fragment, true, true)
    }
}