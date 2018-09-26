package vn.com.fpt.mobinet_fcam.ui.base

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import vn.com.fpt.mobinet_fcam.R
import vn.com.fpt.mobinet_fcam.dagger.component.ApplicationComponent
import vn.com.fpt.mobinet_fcam.dagger.component.DaggerApplicationComponent
import vn.com.fpt.mobinet_fcam.dagger.connect.ApiConfigType
import vn.com.fpt.mobinet_fcam.dagger.module.ApplicationModule
import vn.com.fpt.mobinet_fcam.dagger.module.NetworkModule

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
class BaseApplication : MultiDexApplication() {
    companion object {
        lateinit var instance: BaseApplication private set
    }

    lateinit var baseApp: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupInjector()
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.Helvetica))
                .setFontAttrId(R.attr.fontPath)
                .build())
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun setupInjector() {
        baseApp = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .networkModule(NetworkModule(ApiConfigType.DEVELOP))
                .build()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return baseApp
    }
}