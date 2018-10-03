package vn.com.fpt.mobinet_fcam.ui.contract.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_cable.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.DetailContractModel
import vn.com.fpt.mobinet_fcam.data.network.model.ResponseModel
import vn.com.fpt.mobinet_fcam.data.network.model.SingleChoiceModel
import vn.com.fpt.mobinet_fcam.data.network.model.TitleAndMenuModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.others.datacore.DataCore
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
class UpdateContractFragment : BaseFragment(), UpdateContractContract.UpdateContractView {
    @Inject
    lateinit var presenter: UpdateContractPresenter

    private lateinit var infoContract: DetailContractModel
    private var listInDoor = ArrayList<SingleChoiceModel>()
    private var listOutDoor = ArrayList<SingleChoiceModel>()
    private var listOtherCable = ArrayList<SingleChoiceModel>()
    private var listRouter = ArrayList<SingleChoiceModel>()
    private var indexInDoor = 0
    private var indexOutDoor = 0
    private var indexOtherCable = 0
    private var indexRouter = 0
    private var serviceType = 0

    companion object {
        fun newInstance(item: DetailContractModel,serviceType: Int): UpdateContractFragment {
            val args = Bundle()
            args.putParcelable(Constants.MODEL, item)
            args.putInt(Constants.PARAM_SERVICE_TYPE, serviceType)
            val fragment = UpdateContractFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_update_contract, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {
        setTitle(TitleAndMenuModel(title = getString(R.string.update_contract)))
        arguments?.let {
            infoContract = it.getParcelable(Constants.MODEL)
            serviceType = it.getInt(Constants.PARAM_SERVICE_TYPE)
        }
        getDefaultDataList()
        initSingleDialog()
    }

    private fun initSingleDialog() {
        fragUpdateContract_tvInDoor.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager = fragmentManager,title = getString(R.string.update_indoor),view = fragUpdateContract_tvInDoor,listData = listInDoor,itemSelected = indexInDoor) }
        fragUpdateContract_tvOutDoor.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager = fragmentManager,title = getString(R.string.update_outdoor),view = fragUpdateContract_tvOutDoor,listData = listOutDoor,itemSelected = indexOutDoor) }
        fragUpdateContract_tvOtherCable.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager = fragmentManager,title = getString(R.string.type_cable_other_cable),view = fragUpdateContract_tvOtherCable,listData = listOtherCable,itemSelected = indexOtherCable) }
        fragUpdateContract_tvRouter.setOnClickListener { AppUtils.showDialogSingChoice(fragmentManager = fragmentManager,title = getString(R.string.update_router_amount),view = fragUpdateContract_tvRouter,listData = listRouter,itemSelected = indexRouter) }
    }

    private fun getDefaultDataList() {
        listInDoor = DataCore.getListCable(context)
        listOutDoor = DataCore.getListCable(context)
        listOtherCable = DataCore.getListOtherCable(context)
        listRouter = DataCore.getListRouter(context)
    }

    override fun loadUpdateContract(response: ResponseModel) {

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