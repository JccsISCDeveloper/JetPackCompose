package com.jccsisc.irepcp.core.di

import com.jccsisc.irepcp.login.data.remote.LoginClient
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

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://copec-irep-auth-qa.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providerLoginClient(retrofit: Retrofit): LoginClient = retrofit.create(LoginClient::class.java)

}