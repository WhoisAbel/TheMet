package io.github.metmuseum.themet.arts.ui.list

import io.github.metmuseum.themet.arts.model.ArtIdList
import io.github.metmuseum.themet.common.network.Exceptions

sealed class ArtIdListUiState {
    data class Success(val artIdList: ArtIdList) : ArtIdListUiState()
    data class Error(val exceptions: Exceptions) : ArtIdListUiState()
    object Idle : ArtIdListUiState()
    object Loading : ArtIdListUiState()
}