package vn.com.fpt.mobinet_fcam.ui.contract.detail

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_detail_contract.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.DetailContractKeyValueModel
import vn.com.fpt.mobinet_fcam.data.network.model.DetailContractModel
import vn.com.fpt.mobinet_fcam.data.network.model.TitleAndMenuModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.others.datacore.DataCore
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
import vn.com.fpt.mobinet_fcam.ui.contract.detail.adpater.DetailContractAdapter
import vn.com.fpt.mobinet_fcam.ui.image.view_image.ViewImageFragment
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
class DetailContractFragment : BaseFragment(), DetailContractContract.DetailContractView {
    @Inject
    lateinit var presenter: DetailContractPresenter

    private var objId = 0
    private var contractNumber = ""
    private var listDetailKeyValue = ArrayList<DetailContractKeyValueModel>()
    private lateinit var adapterDetail: DetailContractAdapter

    companion object {
        fun newInstance(objId: Int, contract: String): DetailContractFragment {
            val args = Bundle()
            args.putInt(Constants.PARAM_OBJ_ID, objId)
            args.putString(Constants.TYPE_CONTRACT, contract)
            val fragment = DetailContractFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_contract, container, false)
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
            objId = it.getInt(Constants.PARAM_OBJ_ID)
            contractNumber = it.getString(Constants.TYPE_CONTRACT)
        }
        setTitle(TitleAndMenuModel(title = getString(R.string.info_contract)))
        initParams()
    }

    private fun initParams() {
        presenter.let {
            showLoading()
            val map = HashMap<String, Any>()
            map[Constants.PARAM_OBJ_ID] = objId
            it.getDetailDeployment(map)
        }
    }

    override fun loadDetailContract(response: DetailContractModel) {
        listDetailKeyValue = DataCore.getListDetailContract(context)
        listDetailKeyValue[Constants.FIRST_ITEM].value = contractNumber
        val map: HashMap<String, Any> = Gson().fromJson(Gson().toJson(response), object : TypeToken<HashMap<String, Any>>() {}.type)
        listDetailKeyValue.forEach { itemDetail ->
            map.forEach {
                if (itemDetail.key == it.key)
                    itemDetail.value = it.value.toString()
            }
        }
        adapterDetail = DetailContractAdapter { addFragment(ViewImageFragment.newInstance(listDetailKeyValue[it].value), true, true) }
        adapterDetail.submitList(listDetailKeyValue)
        adapterDetail.notifyDataSetChanged()
        fragDetailContract_rvMain.apply {
            val layout = LinearLayoutManager(context)
            layoutManager = layout
            setHasFixedSize(true)
            adapter = adapterDetail
        }
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