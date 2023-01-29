package io.github.metmuseum.themet.arts.usecase

import io.github.metmuseum.themet.arts.model.ArtIdList
import io.github.metmuseum.themet.common.network.Resource
import javax.inject.Inject

class GetArtIdListImp @Inject constructor(
    private val repository: ArtsRepository
) : GetArtIdList {

    override suspend fun invoke(searchKey: String, hasImages: Boolean): Resource<ArtIdList> =
         repository.getArtIdList(searchKey, hasImages)
}