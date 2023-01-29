package io.github.metmuseum.themet.arts.usecase

import io.github.metmuseum.themet.arts.model.ArtDetails
import io.github.metmuseum.themet.arts.model.ArtIdList
import io.github.metmuseum.themet.common.network.Resource


interface ArtsRepository {

    suspend fun getArtIdList(
        searchKey: String,
        hasImages: Boolean
    ): Resource<ArtIdList>

    suspend fun getArtDetails(
        artId: Int
    ): Resource<ArtDetails>
}