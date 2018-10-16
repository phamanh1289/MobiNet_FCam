package vn.com.fpt.mobinet_fcam.ui.utilities.last_accsee_error

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_last_access_error.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.LastAccessErrorModel
import vn.com.fpt.mobinet_fcam.data.network.model.SearchContractModel
import vn.com.fpt.mobinet_fcam.data.network.model.TitleAndMenuModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
import vn.com.fpt.mobinet_fcam.ui.utilities.last_accsee_error.adapter.LastAccessErrorAdapter
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
class LastAccessErrorFragment : BaseFragment(), LastAccessErrorContract.LastAccessErrorView {
    @Inject
    lateinit var presenter: LastAccessErrorPresenter
    private var searchContractModel: SearchContractModel? = null
    private var listLastAccessErrorModel = ArrayList<LastAccessErrorModel>()
    private lateinit var adapterError: LastAccessErrorAdapter

    companion object {
        fun newInstance(model: SearchContractModel): LastAccessErrorFragment {
            val args = Bundle()
            args.putParcelable(Constants.MODEL, model)
            val fragment = LastAccessErrorFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_last_access_error, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {
        setTitle(TitleAndMenuModel(title = getString(R.string.utilities_access_error).replace("\n", " ")))
        arguments?.let {
            searchContractModel = it.getParcelable(Constants.MODEL) ?: SearchContractModel()
        }
        initRecyclerViewAccess()
        initParamGetAccessError()
    }

    private fun initRecyclerViewAccess() {
        adapterError = LastAccessErrorAdapter()
        fragLastAccessError_rvMain.apply {
            val layout = LinearLayoutManager(context)
            layout.orientation = LinearLayoutManager.VERTICAL
            layoutManager = layout
            adapter = adapterError
            setHasFixedSize(true)
        }
    }

    private fun initParamGetAccessError() {
        showLoading()
        presenter.getLastAccessError(searchContractModel?.objid.toString())
    }

    override fun loadLastAccessError(response: Any) {
        handleDataAccessError(response)
    }

    private fun handleDataAccessError(response: Any) {
        listLastAccessErrorModel = Gson().fromJson(Gson().toJson(response), object : TypeToken<ArrayList<LastAccessErrorModel>>() {}.type)
        adapterError.submitList(listLastAccessErrorModel)
        adapterError.notifyDataSetChanged()
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