package io.github.metmuseum.themet.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.metmuseum.themet.arts.usecase.GetArtDetails
import io.github.metmuseum.themet.arts.usecase.GetArtDetailsImp
import io.github.metmuseum.themet.arts.usecase.GetArtIdList
import io.github.metmuseum.themet.arts.usecase.GetArtIdListImp


@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {


    @Binds
    abstract fun bindGetRandomActivity(impl: GetArtDetailsImp): GetArtDetails

    @Binds
    abstract fun bindSaveActivity(impl: GetArtIdListImp): GetArtIdList

}