package vn.com.fpt.mobinet_fcam.ui.image.view_image

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase
import kotlinx.android.synthetic.main.fragment_view_image.*
import vn.com.fpt.mobinet_fcam.R
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
class ViewImageFragment : BaseFragment(), ViewImageContract.ViewImageView {
    @Inject
    lateinit var presenter: ViewImagePresenter
    private var urlImage = ""

    companion object {
        const val MIN_ZOOM = 2f
        const val MAX_ZOOM = 4f
        fun newInstance(urlImage: String): ViewImageFragment {
            val args = Bundle()
            args.putString(Constants.TYPE_IMAGE, urlImage)
            val fragment = ViewImageFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        activity?.let { KeyboardUtils.setupUI(view, activity = it) }
        initView()
    }

    private fun initView() {
        setTitle(TitleAndMenuModel(title = getString(R.string.view_survey)))
        arguments?.let {
            urlImage = it.getString(Constants.TYPE_IMAGE) ?: ""
        }
        val url = getString(R.string.url_get_image, urlImage)
        val converted = Base64.encodeToString(url.toByteArray(),
                Base64.NO_WRAP)
        showLoading()
        presenter.getImage(converted)
    }

    override fun loadImage(response: String?) {
        response?.let {
            val decode = Base64.decode(it.trim(), Base64.DEFAULT)
            val decodedByte = BitmapFactory.decodeByteArray(decode, 0, decode.size)
            fragViewImageDetail_imgView.displayType = ImageViewTouchBase.DisplayType.FIT_IF_BIGGER
            fragViewImageDetail_imgView.setImageBitmap(decodedByte, null, MIN_ZOOM, MAX_ZOOM)
        }
        hideLoading()
    }

    override fun handleError(response: String) {
        hideLoading()
        AppUtils.showDialog(fragmentManager, title = getString(R.string.mess_error_data), content = response, confirmDialogInterface = null)
    }
}