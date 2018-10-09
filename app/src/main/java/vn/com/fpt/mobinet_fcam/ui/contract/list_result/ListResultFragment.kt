package vn.com.fpt.mobinet_fcam.ui.contract.list_result

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_list_result.*
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.interfaces.MenuMaintenanceDialogInterface
import vn.com.fpt.mobinet_fcam.data.network.model.EmployeeModel
import vn.com.fpt.mobinet_fcam.data.network.model.InfoContractModel
import vn.com.fpt.mobinet_fcam.data.network.model.ResponseModel
import vn.com.fpt.mobinet_fcam.data.network.model.TitleAndMenuModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
import vn.com.fpt.mobinet_fcam.ui.contract.detail.DetailContractFragment
import vn.com.fpt.mobinet_fcam.ui.contract.list_result.adapter.ListDeploymentAdapter
import vn.com.fpt.mobinet_fcam.ui.contract.list_result.adapter.ListMaintenanceAdapter
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
class ListResultFragment : BaseFragment(), ListResultContract.DetailResultView {
    @Inject
    lateinit var presenter: ListResultPresenter

    private var listDataContract = ArrayList<InfoContractModel>()
    private var listEmployee = ArrayList<EmployeeModel>()
    private var mAdapterDeployment: ListDeploymentAdapter? = null
    private var mAdapterMaintenance: ListMaintenanceAdapter? = null
    private var paramsJson = ""
    private var typeInfo = 0
    private var serviceType = 0
    private var typeContract = ""

    companion object {
        fun newInstance(json: String, typeContract: String, info: Int, serviceType: Int): ListResultFragment {
            val args = Bundle()
            args.putString(Constants.PARAMS_JSON, json)
            args.putString(Constants.TYPE_CONTRACT, typeContract)
            args.putInt(Constants.TYPE_INFO, info)
            args.putInt(Constants.PARAM_SERVICE_TYPE, serviceType)
            val fragment = ListResultFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_result, container, false)
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
            paramsJson = it.getString(Constants.PARAMS_JSON) ?: ""
            typeContract = it.getString(Constants.TYPE_CONTRACT)
            typeInfo = it.getInt(Constants.TYPE_INFO)
            serviceType = it.getInt(Constants.PARAM_SERVICE_TYPE)
            //paramsJson ={"ChecklistType":1,"Type":2,"Username":"SIR3-Pitou.Pich","Todate":"10-09-2018","Fromdate":"10-02-2018"}
            //typeContract = maintenance
            //typeInfo = 1
            //serviceType = 2
        }
        setTitle(TitleAndMenuModel(title = getString(R.string.info_contract)))
        getListMemberOfTeam()
    }

    private fun initParams() {
        presenter.let {
            val map: HashMap<String, Any> = Gson().fromJson(paramsJson, object : TypeToken<HashMap<String, Any>>() {}.type)
            map[Constants.PARAM_CONTRACT_TYPE_UPPER] = typeInfo
            if (typeContract == Constants.CONTRACT_DEPLOYMENT)
                it.getListContractDepl(map)
            else it.getListInfoContractMain(map)
        }
    }

    private fun getListMemberOfTeam() {
        presenter.let {
            showLoading()
            val map = HashMap<String, Any>()
            map[Constants.PARAM_USER_NAME_UPPER_FULL] = getDefaultUser()?.mobiaccount.toString()
            map[Constants.PARAM_IMEI] = getSharePreferences().imeiDevice
            it.getListMemberOfTeam(map)
        }
    }

    private fun handleDataListContract(list: Any) {
        listDataContract = Gson().fromJson(Gson().toJson(list), object : TypeToken<ArrayList<InfoContractModel>>() {}.type)
//        listDataContract.add(demoObj)
        when (typeContract) {
            Constants.CONTRACT_DEPLOYMENT -> {
                mAdapterDeployment = ListDeploymentAdapter {
                    addFragment(DetailContractFragment.newInstance(listDataContract[it].objid, Constants.CONTRACT_DEPLOYMENT, listDataContract[it].contract, serviceType), true, true)
                }
                mAdapterDeployment?.apply {
                    submitList(listDataContract)
                    notifyDataSetChanged()
                }
            }
            else -> {
                mAdapterMaintenance = ListMaintenanceAdapter {
                    AppUtils.showDialogMenuMaintenance(fragmentManager, listDataContract[it].contract, listEmployee, object : MenuMaintenanceDialogInterface {
                        override fun actionAssign() {

                        }

                        override fun actionAccept() {

                        }

                        override fun actionNext() {
                            addFragment(DetailContractFragment.newInstance(listDataContract[it].objid, Constants.CONTRACT_MAINTENANCE, listDataContract[it].contract, serviceType), true, true)
                        }
                    })
                }
                mAdapterMaintenance?.apply {
                    submitList(listDataContract)
                    notifyDataSetChanged()
                }
            }
        }
        fragListResult_rvMain.apply {
            val layout = LinearLayoutManager(context)
            layoutManager = layout
            setHasFixedSize(true)
            adapter = mAdapterDeployment ?: mAdapterMaintenance
        }
        hideLoading()
    }

    private fun handleDataMemberOfTeam(list: Any) {
        listEmployee = Gson().fromJson(Gson().toJson(list), object : TypeToken<ArrayList<EmployeeModel>>() {}.type)
        initParams()
    }

    override fun loadMemberOfTeam(response: ResponseModel) {
        handleDataMemberOfTeam(response.list)
    }

    override fun loadListContract(response: ResponseModel) {
        handleDataListContract(response.list)
    }

    override fun handleError(response: String) {
        hideLoading()
        AppUtils.showDialog(fragmentManager, title = getString(R.string.mess_error_data), content = response, confirmDialogInterface = null)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    val demoObj = InfoContractModel(
            id = 6906582,
            objid = 573312,
            contract = "PPDD22006",
            datecreate = "2018-10-09T10:52:34.857",
            dateassign = "",
            typecus = "",
            priority = "",
            fullname = "Chan Huy Chem",
            address = "133 St.217, Sangkat Orussey 3, Khan 7Makara, Phnom Penh",
            appointmentdate = "2018-10-09T12:00:00",
            totalchecklist = 48,
            totalchecklistinmonth = 2,
            hourremain = "1:0:3060"
    )

}