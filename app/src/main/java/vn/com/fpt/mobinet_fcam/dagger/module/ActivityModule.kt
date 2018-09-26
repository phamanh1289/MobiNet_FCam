package vn.com.fpt.mobinet_fcam.dagger.module

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import vn.com.fpt.mobinet_fcam.dagger.scope.ActivityScope

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
@ActivityScope
@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): Activity {
        return activity
    }
}