package vn.com.fpt.mobinet_fcam.ui.utilities

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_utilities.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.MenuModel
import vn.com.fpt.mobinet_fcam.data.network.model.SearchContractModel
import vn.com.fpt.mobinet_fcam.data.network.model.TitleAndMenuModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.others.datacore.DataCore
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
import vn.com.fpt.mobinet_fcam.ui.info.InfoFragment
import vn.com.fpt.mobinet_fcam.ui.utilities.adapter.UtilitiesAdapter
import vn.com.fpt.mobinet_fcam.ui.utilities.connect_property.ConnectPropertyFragment
import vn.com.fpt.mobinet_fcam.ui.utilities.kill_session.KillSessionFragment
import vn.com.fpt.mobinet_fcam.ui.utilities.last_accsee_error.LastAccessErrorFragment
import vn.com.fpt.mobinet_fcam.ui.utilities.list_connection.ListConnectFragment
import vn.com.fpt.mobinet_fcam.ui.utilities.online_connection.OnlineConnectionFragment
import vn.com.fpt.mobinet_fcam.ui.utilities.reset_mac.ResetMacFragment
import vn.com.fpt.mobinet_fcam.ui.utilities.reset_password.ResetPasswordFragment
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
class UtilitiesFragment : BaseFragment(), UtilitiesContract.UtilitiesView {
    @Inject
    lateinit var presenter: UtilitiesPresenter

    private lateinit var searchContractModel: SearchContractModel
    private lateinit var adapterUtilities: UtilitiesAdapter
    private var listUtilities = ArrayList<MenuModel>()

    companion object {
        const val MAX_COL = 3
        fun newInstance(model: SearchContractModel): UtilitiesFragment {
            val args = Bundle()
            args.putParcelable(Constants.MODEL, model)
            val fragment = UtilitiesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_utilities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {
        arguments?.let {
            searchContractModel = it.getParcelable(Constants.MODEL) ?: SearchContractModel()
        }
        setTitle(TitleAndMenuModel(title = getString(R.string.menu_utilities)))
        listUtilities = DataCore.getListUtilities(context)
        adapterUtilities = UtilitiesAdapter { handleOnClick(it) }
        adapterUtilities.run {
            submitList(listUtilities)
            fragUtilities_rvMain?.apply {
                adapter = this@run
                val layout = GridLayoutManager(context, MAX_COL)
                layoutManager = layout
                setHasFixedSize(true)
            }
            notifyDataSetChanged()
        }
    }

    private fun handleOnClick(position: Int) {
        addFragment(when (position) {
            Constants.UTILITIES_CONNECTION -> {
                ConnectPropertyFragment.newInstance(searchContractModel)
            }
            Constants.UTILITIES_KILL_SESSION -> {
                KillSessionFragment.newInstance(searchContractModel)
            }
            Constants.UTILITIES_LIST_CONNECTION -> {
                ListConnectFragment.newInstance(searchContractModel)
            }
            Constants.UTILITIES_RESET_MAC -> {
                ResetMacFragment.newInstance(searchContractModel)
            }
            Constants.UTILITIES_RESET_PASSWORD -> {
                ResetPasswordFragment.newInstance(searchContractModel)
            }
            Constants.UTILITIES_ONLINE -> {
                OnlineConnectionFragment.newInstance(searchContractModel)
            }
            Constants.UTILITIES_ERROR -> {
                LastAccessErrorFragment.newInstance(searchContractModel)
            }
//            Constants.UTILITIES_FTTH -> {
//            }
//            Constants.UTILITIES_NEW_NANO -> {
//            }
//            Constants.UTILITIES_CARD_NANO -> {
//            }
            else -> InfoFragment() //Else : MENU_INFO
        }, true, true)
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