package io.github.metmuseum.themet.arts.ui.details

import io.github.metmuseum.themet.arts.model.ArtDetails
import io.github.metmuseum.themet.common.network.Exceptions

sealed class ArtDetailsListUiState {
    data class Success(val artDetails: ArtDetails) : ArtDetailsListUiState()
    data class Error(val exceptions: Exceptions) : ArtDetailsListUiState()
    object Idle : ArtDetailsListUiState()
    object Loading : ArtDetailsListUiState()
}