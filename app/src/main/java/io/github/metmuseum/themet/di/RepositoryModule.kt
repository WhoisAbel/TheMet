package io.github.metmuseum.themet.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.metmuseum.themet.arts.repositories.ArtsRepositoryImp
import io.github.metmuseum.themet.arts.usecase.ArtsRepository


@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindArtRepository(impl: ArtsRepositoryImp): ArtsRepository

}