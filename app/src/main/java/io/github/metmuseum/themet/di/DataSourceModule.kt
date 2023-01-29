package io.github.metmuseum.themet.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.metmuseum.themet.arts.dataSource.ArtsRemoteDataSourceImp
import io.github.metmuseum.themet.arts.repositories.ArtsRemoteDataSource


@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindArtsRemoteDataSource(impl: ArtsRemoteDataSourceImp): ArtsRemoteDataSource

}