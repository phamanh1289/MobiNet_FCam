package vn.com.fpt.mobinet_fcam.ui.utilities.pico

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.data.network.model.SearchContractModel
import vn.com.fpt.mobinet_fcam.data.network.model.TitleAndMenuModel
import vn.com.fpt.mobinet_fcam.others.constant.Constants
import vn.com.fpt.mobinet_fcam.ui.base.BaseFragment
import vn.com.fpt.mobinet_fcam.utils.KeyboardUtils

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class PicoFragment : BaseFragment(){
//    @Inject
//    lateinit var presenter: BlankPresenter
private var searchContractModel: SearchContractModel? = null

    companion object {
        fun newInstance(model: SearchContractModel): PicoFragment {
            val args = Bundle()
            args.putParcelable(Constants.MODEL, model)
            val fragment = PicoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        getActivityComponent().inject(this)
//        presenter.onAttach(this)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {  setTitle(TitleAndMenuModel(title = getString(R.string.utilities_connection).replace("\n", " ")))
        arguments?.let {
            searchContractModel = it.getParcelable(Constants.MODEL) ?: SearchContractModel()
        }
    }

//    override fun loadLogin(response: ResponseModel) {
//
//    }

//    override fun handleError(response: String) {
//        hideLoading()
//        AppUtils.showDialog(fragmentManager, title = getString(R.string.mess_error_data), content = response, confirmDialogInterface = null)
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        presenter.onDetach()
//    }
}