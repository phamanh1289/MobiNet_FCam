package vn.com.fpt.mobinet_fcam.ui.utilities.online_connection

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_online_connection.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.KillSessionModel
import vn.com.fpt.mobinet_fcam.data.network.model.SearchContractModel
import vn.com.fpt.mobinet_fcam.data.network.model.TitleAndMenuModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
import vn.com.fpt.mobinet_fcam.ui.utilities.online_connection.adapter.OnlineConnectionAdapter
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
class OnlineConnectionFragment : BaseFragment(), OnlineConnectionContract.OnlineConnectionView {
    @Inject
    lateinit var presenter: OnlineConnectionPresenter
    private var searchContractModel: SearchContractModel? = null
    private lateinit var adapterConnection: OnlineConnectionAdapter
    private var listConnection = ArrayList<KillSessionModel>()

    companion object {
        fun newInstance(model: SearchContractModel): OnlineConnectionFragment {
            val args = Bundle()
            args.putParcelable(Constants.MODEL, model)
            val fragment = OnlineConnectionFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_online_connection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {
        setTitle(TitleAndMenuModel(title = getString(R.string.utilities_online_connect).replace("\n", " ")))
        arguments?.let {
            searchContractModel = it.getParcelable(Constants.MODEL) ?: SearchContractModel()
        }
        initRecyclerViewConnection()
        initParamGetListOnline()
    }

    private fun initParamGetListOnline() {
        showLoading()
        presenter.getOnlineConnection(searchContractModel?.objid.toString())
    }

    private fun initRecyclerViewConnection() {
        adapterConnection = OnlineConnectionAdapter()
        fragOnlineConnection_rvMain.apply {
            val layout = LinearLayoutManager(context)
            layout.orientation = LinearLayoutManager.VERTICAL
            layoutManager = layout
            adapter = adapterConnection
            setHasFixedSize(true)
        }
    }

    override fun loadOnlineConnection(response: Any) {
        handleDataOnlineConnection(response)
    }

    private fun handleDataOnlineConnection(response: Any) {
        try {
            listConnection.add(Gson().fromJson(Gson().toJson(response), KillSessionModel::class.java))
        } catch (e: Exception) {
            listConnection = Gson().fromJson(Gson().toJson(response), object : TypeToken<ArrayList<KillSessionModel>>() {}.type)
        }
        adapterConnection.submitList(listConnection)
        adapterConnection.notifyDataSetChanged()
        hideLoading()
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