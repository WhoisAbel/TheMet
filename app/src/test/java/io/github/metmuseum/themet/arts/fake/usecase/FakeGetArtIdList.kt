package io.github.metmuseum.themet.arts.fake.usecase

import io.github.metmuseum.themet.arts.artIdList1
import io.github.metmuseum.themet.arts.model.ArtIdList
import io.github.metmuseum.themet.arts.usecase.GetArtIdList
import io.github.metmuseum.themet.common.network.Exceptions
import io.github.metmuseum.themet.common.network.Resource

    /*
     * Pass a Boolean argument in a constructor, which will change the behavior of our fake.
     */
class FakeGetArtIdList(
    private val isSuccessful: Boolean = true
) : GetArtIdList {

    /*
     * As we can see, since the data comes from the network, our use case returns
     * a wrapper Resource<T>, which can either be a Success or Error.
     */
    override suspend fun invoke(searchKey: String, hasImages: Boolean): Resource<ArtIdList> {
        return if (isSuccessful) {
            Resource.Success(artIdList1)
        } else {
            Resource.Error(Exceptions.RemoteDataSourceException("Boom..."))
        }
    }
}