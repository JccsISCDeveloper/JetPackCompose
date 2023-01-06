package com.jccsisc.irepcp.core.di

import com.jccsisc.irepcp.core.constants.ConstantsApi.BASE_URL_RICK_AND_MORTY
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.data.remote.RickAndMortyApi
import com.jccsisc.irepcp.ui.activities.login.screens.login.data.remote.LoginClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.core.di
 * Created by Julio Cesar Camacho Silva on 15/11/22
 */
@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    /**
     * Login IREP COPEC todo no está terminado
     * */
    @Provides
    @Singleton
    fun provideRetrofit(): LoginClient = Retrofit.Builder()
        .baseUrl("https://copec-irep-auth-qa.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LoginClient::class.java)

    /**
     * API Rick and Morty
     * */
    @Provides
    @Singleton
    fun providerRickAndMortyApi(): RickAndMortyApi = Retrofit.Builder()
        .baseUrl(BASE_URL_RICK_AND_MORTY)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RickAndMortyApi::class.java)


}