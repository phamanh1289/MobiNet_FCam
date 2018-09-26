package vn.com.fpt.mobinet_fcam.dagger.connect

/**
 * *******************************************
 * * Created by AnhPT76 on 26/09/2018.      **
 * * Copyright (c)  by FPT Telecom          **
 * * All rights reserved                    **
 * *******************************************
 */
enum class ApiConfigType(var title: String?) {
    STAGING("staging"),
    DEVELOP("develop"),
    PRELIVE("prelive"),
    LIVE("live")
}