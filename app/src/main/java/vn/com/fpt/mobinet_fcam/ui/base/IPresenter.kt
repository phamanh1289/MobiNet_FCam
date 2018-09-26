package vn.com.fpt.mobinet_fcam.ui.base

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
interface IPresenter<V : BaseView> {
    val isViewAttach: Boolean
    fun onAttach(mvpView: V)
    fun onDetach()
}