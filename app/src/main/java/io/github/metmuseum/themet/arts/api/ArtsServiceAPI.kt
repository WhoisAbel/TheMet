package io.github.metmuseum.themet.arts.api

import io.github.metmuseum.themet.arts.model.ArtDetails
import io.github.metmuseum.themet.arts.model.ArtIdList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtsServiceAPI {

    @GET("/public/collection/v1/search")
    suspend fun getArtIdList(
        @Query("q") searchKey: String,
        @Query("hasImages") hasImages: Boolean = true
    ): Response<ArtIdList>

    @GET("/public/collection/v1/objects/{artId}")
    suspend fun getArtDetails(
        @Path("artId") artId: Int
    ): Response<ArtDetails>

}