package vn.com.fpt.mobinet_fcam.ui.utilities.list_connection

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_list_connection.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.KillSessionModel
import vn.com.fpt.mobinet_fcam.data.network.model.SearchContractModel
import vn.com.fpt.mobinet_fcam.data.network.model.TitleAndMenuModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
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
class ListConnectFragment : BaseFragment(), ListConnectContract.ListConnectView {

    @Inject
    lateinit var presenter: ListConnectPresenter
    private var searchContractModel: SearchContractModel? = null

    companion object {
        fun newInstance(model: SearchContractModel): ListConnectFragment {
            val args = Bundle()
            args.putParcelable(Constants.MODEL, model)
            val fragment = ListConnectFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_connection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {
        setTitle(TitleAndMenuModel(title = getString(R.string.utilities_list_connection).replace("\n", " ")))
        arguments?.let {
            searchContractModel = it.getParcelable(Constants.MODEL) ?: SearchContractModel()
        }
        //call api get list connect
        showLoading()
        presenter.getListConnection(searchContractModel?.objid.toString())
    }

    private fun initRecyclerViewListConnect() {
        fragListConnection_rvMain.apply {
            val layout = LinearLayoutManager(context)
            layout.orientation = LinearLayoutManager.VERTICAL
            layoutManager = layout
            setHasFixedSize(true)
        }
    }

    private fun handleDataListConnect(data: String) {
        val listConnect: ArrayList<KillSessionModel> = Gson().fromJson(data, object : TypeToken<ArrayList<KillSessionModel>>() {}.type)
        hideLoading()
    }

    override fun loadListConnection(response: Any) {
        handleDataListConnect(response.toString())
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