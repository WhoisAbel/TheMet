package io.github.metmuseum.themet.arts.usecase

import io.github.metmuseum.themet.arts.model.ArtDetails
import io.github.metmuseum.themet.common.network.Resource
import javax.inject.Inject

class GetArtDetailsImp @Inject constructor(
    private val repository: ArtsRepository
) : GetArtDetails {

    override suspend fun invoke(artId: Int): Resource<ArtDetails> =
        repository.getArtDetails(artId)
}