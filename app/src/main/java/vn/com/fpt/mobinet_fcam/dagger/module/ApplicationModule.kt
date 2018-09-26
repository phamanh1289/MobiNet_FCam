package vn.com.fpt.mobinet_fcam.dagger.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
@Module
class ApplicationModule(private val baseApplication: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Application {
        return baseApplication
    }
}