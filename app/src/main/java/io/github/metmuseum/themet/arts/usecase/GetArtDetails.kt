package io.github.metmuseum.themet.arts.usecase

import io.github.metmuseum.themet.arts.model.ArtDetails
import io.github.metmuseum.themet.common.network.Resource

interface GetArtDetails {
    suspend operator fun invoke(artId: Int): Resource<ArtDetails>
}