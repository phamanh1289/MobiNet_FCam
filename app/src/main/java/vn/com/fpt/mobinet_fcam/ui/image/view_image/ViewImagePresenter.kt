package vn.com.fpt.mobinet_fcam.ui.image.view_image

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.com.fpt.mobinet_fcam.data.network.api.ApiService
import vn.com.fpt.mobinet_fcam.ui.base.BasePresenter
import javax.inject.Inject

/**
 * *******************************************
 * * Created by AnhPT76 on 27/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class ViewImagePresenter @Inject constructor(private val apiService: ApiService) : BasePresenter<ViewImageContract.ViewImageView>(), ViewImageContract.ViewImagePresenter {

    override fun getImage(id: String) {
        addSubscribe(apiService.getImage(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    view?.loadImage(it.toString())
                }, {
                    view?.handleError(it.message.toString())
                }))
    }
}