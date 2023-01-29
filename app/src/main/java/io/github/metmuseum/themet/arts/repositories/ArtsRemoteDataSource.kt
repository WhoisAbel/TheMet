package io.github.metmuseum.themet.arts.repositories

import io.github.metmuseum.themet.arts.model.ArtDetails
import io.github.metmuseum.themet.arts.model.ArtIdList
import io.github.metmuseum.themet.common.network.Resource

interface ArtsRemoteDataSource {

    suspend fun getArtIdList(
        searchKey: String,
        hasImages: Boolean
    ): Resource<ArtIdList>

    suspend fun getArtDetails(
        artId: Int
    ): Resource<ArtDetails>
}