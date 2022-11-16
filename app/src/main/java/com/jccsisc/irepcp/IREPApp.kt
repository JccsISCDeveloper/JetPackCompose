package com.jccsisc.irepcp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.core
 * Created by Julio Cesar Camacho Silva on 14/11/22
 */
@HiltAndroidApp
class IREPApp: Application() {

    companion object {
        lateinit var INSTANCE: IREPApp
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
    }

}