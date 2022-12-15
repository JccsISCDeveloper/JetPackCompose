package com.jccsisc.irepcp.core.di

import androidx.lifecycle.MutableLiveData

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.core.di
 * Created by Julio Cesar Camacho Silva on 14/12/22
 */
class MySingletonClass {
    var taskOrder = MutableLiveData("")
    var titleDateFilter = MutableLiveData("Fecha")
    var titlePriorityFilter = MutableLiveData("Prioridad")
}