package vn.com.fpt.mobinet_fcam.dagger.component

import dagger.Subcomponent
import vn.com.fpt.mobinet_fcam.dagger.module.ActivityModule
import vn.com.fpt.mobinet_fcam.dagger.scope.ActivityScope
import vn.com.fpt.mobinet_fcam.ui.contract.detail.DetailContractFragment
import vn.com.fpt.mobinet_fcam.ui.contract.list_result.ListResultFragment
import vn.com.fpt.mobinet_fcam.ui.contract.search_list.SearchListFragment
import vn.com.fpt.mobinet_fcam.ui.contract.update.deployment.UpdateDeploymentFragment
import vn.com.fpt.mobinet_fcam.ui.contract.update.maintenance.UpdateMaintenanceFragment
import vn.com.fpt.mobinet_fcam.ui.functions.FunctionsFragment
import vn.com.fpt.mobinet_fcam.ui.image.view_image.ViewImageFragment
import vn.com.fpt.mobinet_fcam.ui.info.InfoFragment
import vn.com.fpt.mobinet_fcam.ui.login.LoginFragment
import vn.com.fpt.mobinet_fcam.ui.main.MainActivity
import vn.com.fpt.mobinet_fcam.ui.port_net.PortNetFragment
import vn.com.fpt.mobinet_fcam.ui.splash_screen.SplashScreenActivity
import vn.com.fpt.mobinet_fcam.ui.utilities.UtilitiesFragment
import vn.com.fpt.mobinet_fcam.ui.utilities.connect_property.ConnectPropertyFragment
import vn.com.fpt.mobinet_fcam.ui.utilities.kill_session.KillSessionFragment
import vn.com.fpt.mobinet_fcam.ui.utilities.list_connection.ListConnectFragment
import vn.com.fpt.mobinet_fcam.ui.utilities.online_connection.OnlineConnectionFragment
import vn.com.fpt.mobinet_fcam.ui.utilities.reset_mac.ResetMacFragment
import vn.com.fpt.mobinet_fcam.ui.utilities.reset_password.ResetPasswordFragment

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
    fun inject(searchContractFragment: UtilitiesFragment)
    fun inject(portNetFragment: PortNetFragment)
    fun inject(infoFragment: InfoFragment)
    fun inject(listResultFragment: ListResultFragment)
    fun inject(detailContractFragment: DetailContractFragment)
    fun inject(viewImageFragment: ViewImageFragment)
    fun inject(updateDeploymentContractFragment: UpdateDeploymentFragment)
    fun inject(updateMaintenanceFragment: UpdateMaintenanceFragment)
    fun inject(functionsFragment: FunctionsFragment)
    fun inject(connectPropertyFragment: ConnectPropertyFragment)
    fun inject(killSessionFragment: KillSessionFragment)
    fun inject(listConnectFragment: ListConnectFragment)
    fun inject(resetMacFragment: ResetMacFragment)
    fun inject(resetPasswordFragment: ResetPasswordFragment)
    fun inject(onlineConnectionFragment: OnlineConnectionFragment)

}