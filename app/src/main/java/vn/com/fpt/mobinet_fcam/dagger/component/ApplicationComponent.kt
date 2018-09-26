package vn.com.fpt.mobinet_fcam.dagger.component

import dagger.Component
import vn.com.fpt.mobinet_fcam.dagger.module.ApplicationModule
import vn.com.fpt.mobinet_fcam.dagger.module.NetworkModule
import vn.com.fpt.mobinet_fcam.dagger.scope.AppScope
import javax.inject.Singleton

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
@AppScope
@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun getActivityComponent() : ActivityComponent
}