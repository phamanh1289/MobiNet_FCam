package vn.com.fpt.mobinet_fcam.dagger.component

import dagger.Subcomponent
import vn.com.fpt.mobinet_fcam.dagger.module.ActivityModule
import vn.com.fpt.mobinet_fcam.dagger.scope.ActivityScope
import vn.com.fpt.mobinet_fcam.ui.contract.list_result.ListResultFragment
import vn.com.fpt.mobinet_fcam.ui.contract.search.SearchContractFragment
import vn.com.fpt.mobinet_fcam.ui.contract.search_list.SearchListFragment
import vn.com.fpt.mobinet_fcam.ui.info.InfoFragment
import vn.com.fpt.mobinet_fcam.ui.login.LoginFragment
import vn.com.fpt.mobinet_fcam.ui.main.MainActivity
import vn.com.fpt.mobinet_fcam.ui.port_net.PortNetFragment
import vn.com.fpt.mobinet_fcam.ui.splash_screen.SplashScreenActivity

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
@ActivityScope
@Subcomponent(modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(splashScreenActivity: SplashScreenActivity)
    fun inject(loginFragment: LoginFragment)
    fun inject(searchListFragment: SearchListFragment)
    fun inject(searchContractFragment: SearchContractFragment)
    fun inject(portNetFragment: PortNetFragment)
    fun inject(infoFragment: InfoFragment)
    fun inject(detailResultFragment: ListResultFragment)

}