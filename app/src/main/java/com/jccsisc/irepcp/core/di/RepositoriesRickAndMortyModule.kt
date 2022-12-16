package com.jccsisc.irepcp.core.di

import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.data.remote.PersonajeRepositoryImpl
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.domain.repositories.PersonajeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.core.di
 * Created by Julio Cesar Camacho Silva on 06/12/22
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesRickAndMortyModule {

    @Binds
    abstract fun bindPersonajeRepository(impl: PersonajeRepositoryImpl): PersonajeRepository


}