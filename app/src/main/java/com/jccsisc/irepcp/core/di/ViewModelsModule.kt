package com.jccsisc.irepcp.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.core.di
 * Created by Julio Cesar Camacho Silva on 14/12/22
 */
@Module
@InstallIn(SingletonComponent::class)
class ViewModelsModule {

    /**
     * TaskViewModel
     * */
    @Singleton
    @Provides
    fun provideTaskViewModelSingleton(): MySingletonClass {
        return MySingletonClass()
    }

}