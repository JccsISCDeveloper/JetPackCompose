package com.jccsisc.irepcp.core

import android.app.Application

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.core
 * Created by Julio Cesar Camacho Silva on 14/11/22
 */
class IREPApp: Application() {

    companion object {
        lateinit var INSTANCE: IREPApp
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
    }

}