package com.jccsisc.irepcp.core.di

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.core.di
 * Created by Julio Cesar Camacho Silva on 14/12/22
 */
class MySingletonClass {
    var taskOrder = MutableLiveData("")
}