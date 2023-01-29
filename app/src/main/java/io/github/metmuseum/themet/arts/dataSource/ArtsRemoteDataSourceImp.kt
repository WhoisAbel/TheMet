package io.github.metmuseum.themet.arts.dataSource

import io.github.metmuseum.themet.arts.api.ArtsServiceAPI
import io.github.metmuseum.themet.arts.model.ArtDetails
import io.github.metmuseum.themet.arts.model.ArtIdList
import io.github.metmuseum.themet.arts.repositories.ArtsRemoteDataSource
import io.github.metmuseum.themet.common.network.BaseRemoteDataSource
import io.github.metmuseum.themet.common.network.Resource
import io.github.metmuseum.themet.common.network.safeApiCall
import javax.inject.Inject

class ArtsRemoteDataSourceImp @Inject constructor(
    private val artsServiceAPI: ArtsServiceAPI
) : BaseRemoteDataSource(), ArtsRemoteDataSource {

    override suspend fun getArtIdList(
        searchKey: String,
        hasImages: Boolean
    ): Resource<ArtIdList> {
        return safeApiCall(
            call = { requestGetArtIdList(searchKey, hasImages) },
            errorMessage = "Error getting ArtList"
        )
    }

    private suspend fun requestGetArtIdList(
        searchKey: String,
        hasImages: Boolean
    ) = checkApiResult(
        artsServiceAPI.getArtIdList(searchKey, hasImages)
    )


    override suspend fun getArtDetails(artId: Int): Resource<ArtDetails> {
        return safeApiCall(
            call = { requestGetArtDetails(artId) },
            errorMessage = "Error getting details"
        )
    }

    private suspend fun requestGetArtDetails(artId: Int): Resource<ArtDetails> =
        checkApiResult(response = artsServiceAPI.getArtDetails(artId))


}