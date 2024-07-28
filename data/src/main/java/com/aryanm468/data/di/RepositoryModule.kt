package com.aryanm468.data.di

import com.aryanm468.data.data_source.remote.PokemonApi
import com.aryanm468.data.repository.IPokemonRepositoryImpl
import com.aryanm468.domain.repository.IPokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonRepository(pokemonApi: PokemonApi): IPokemonRepository {
        return IPokemonRepositoryImpl(pokemonApi)
    }

}