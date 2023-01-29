package io.github.metmuseum.themet.arts.repositories

import io.github.metmuseum.themet.arts.model.ArtDetails
import io.github.metmuseum.themet.arts.model.ArtIdList
import io.github.metmuseum.themet.arts.usecase.ArtsRepository
import io.github.metmuseum.themet.common.network.Exceptions
import io.github.metmuseum.themet.common.network.NetworkHandler
import io.github.metmuseum.themet.common.network.Resource

import javax.inject.Inject

class ArtsRepositoryImp @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val remoteDataSource: ArtsRemoteDataSource
) : ArtsRepository {


    override suspend fun getArtIdList(
        searchKey: String,
        hasImages: Boolean
    ): Resource<ArtIdList> {
        return if (networkHandler.hasNetworkConnection()) {
            when (val result = remoteDataSource.getArtIdList(searchKey, hasImages)) {
                is Resource.Success -> Resource.Success(result.data)
                is Resource.Error -> Resource.Error(Exceptions.RemoteDataSourceException(result.error.toString()))
            }
        } else
            Resource.Error(Exceptions.NetworkConnectionException())
    }

    override suspend fun getArtDetails(artId: Int): Resource<ArtDetails> {
        return if (networkHandler.hasNetworkConnection()) {
            when (val result = remoteDataSource.getArtDetails(artId)) {
                is Resource.Success -> Resource.Success(result.data)
                is Resource.Error -> Resource.Error(Exceptions.RemoteDataSourceException(result.error.toString()))
            }
        } else
            Resource.Error(Exceptions.NetworkConnectionException())

    }

}