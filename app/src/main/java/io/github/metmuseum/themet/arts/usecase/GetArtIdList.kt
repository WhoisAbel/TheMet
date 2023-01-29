package io.github.metmuseum.themet.arts.usecase

import io.github.metmuseum.themet.arts.model.ArtIdList
import io.github.metmuseum.themet.common.network.Resource

interface GetArtIdList {

    suspend operator fun invoke(
        searchKey: String,
        hasImages: Boolean
    ): Resource<ArtIdList>

}