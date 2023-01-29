package io.github.metmuseum.themet.arts.model

import com.squareup.moshi.Json

data class ArtDetails(
    val objectID: Int,
    val objectName: String?,
    val title: String?,
    val repository: String?,
    val accessionNumber: String?,
    val objectDate: String?,
    @Json(name = "primaryImageSmall")
    val primaryImageSmall: String?,
    val department: String?,
    val dimensions: String?,
    val additionalImages: List<String>?
)