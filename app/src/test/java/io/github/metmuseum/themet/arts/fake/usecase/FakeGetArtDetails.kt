package io.github.metmuseum.themet.arts.fake.usecase

import io.github.metmuseum.themet.arts.artDetails1
import io.github.metmuseum.themet.arts.model.ArtDetails
import io.github.metmuseum.themet.arts.usecase.GetArtDetails
import io.github.metmuseum.themet.common.network.Exceptions
import io.github.metmuseum.themet.common.network.Resource


    /*
     * Pass a Boolean argument in a constructor, which will change the behavior of our fake.
     */
class FakeGetArtDetails(
    private val isSuccessful: Boolean = true
) : GetArtDetails {

    /*
     * As we can see, since the data comes from the network, our use case returns
     * a wrapper Resource<T>, which can either be a Success or Error.
     */
    override suspend fun invoke(artId: Int): Resource<ArtDetails> {
        return if (isSuccessful) {
            Resource.Success(artDetails1)
        } else {
            Resource.Error(Exceptions.RemoteDataSourceException("Boom..."))
        }
    }
}