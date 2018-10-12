package vn.com.fpt.mobinet_fcam.ui.utilities.connect_property

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_connect_property.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.ConnectPropertyModel
import vn.com.fpt.mobinet_fcam.data.network.model.DetailContractKeyValueModel
import vn.com.fpt.mobinet_fcam.data.network.model.SearchContractModel
import vn.com.fpt.mobinet_fcam.data.network.model.TitleAndMenuModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.others.datacore.DataCore
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
import vn.com.fpt.mobinet_fcam.ui.utilities.connect_property.adapter.ConnectPropertyAdapter
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
class ConnectPropertyFragment : BaseFragment(), ConnectPropertyContract.ConnectPropertyView {

    @Inject
    lateinit var presenter: ConnectPropertyPresenter
    private var searchContractModel: SearchContractModel? = null
    private lateinit var adapterConnect: ConnectPropertyAdapter
    private var listDetailKeyValue = ArrayList<DetailContractKeyValueModel>()

    companion object {
        const val MAX_COL = 2
        fun newInstance(model: SearchContractModel): ConnectPropertyFragment {
            val args = Bundle()
            args.putParcelable(Constants.MODEL, model)
            val fragment = ConnectPropertyFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_connect_property, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {
        setTitle(TitleAndMenuModel(title = getString(R.string.utilities_connection).replace("\n", " ")))
        arguments?.let {
            searchContractModel = it.getParcelable(Constants.MODEL) ?: SearchContractModel()
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapterConnect = ConnectPropertyAdapter()
        listDetailKeyValue = DataCore.getListConnectProperty(context)
        adapterConnect.submitList(listDetailKeyValue)
        fragConnectProperty_rvMain.apply {
            adapter = adapterConnect
            val layout = GridLayoutManager(context, MAX_COL)
            layoutManager = layout
            setHasFixedSize(true)
        }
        initParamGetConnect()
    }

    private fun initParamGetConnect() {
        showLoading()
        presenter.getConnectProperty(searchContractModel?.objid.toString())
    }

    override fun loadConnectProperty(response: ConnectPropertyModel) {
        val map: HashMap<String, String> = Gson().fromJson(Gson().toJson(response), object : TypeToken<HashMap<String, String>>() {}.type)
        listDetailKeyValue.forEach { item ->
            map.forEach {
                if (it.key == item.key)
                    item.value = it.value
            }
        }
        adapterConnect.notifyDataSetChanged()
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