package io.github.metmuseum.themet.arts.ui.details

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.metmuseum.themet.arts.usecase.GetArtDetails
import io.github.metmuseum.themet.common.network.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtDetailsViewModel @Inject constructor(
    private val getArtDetails: GetArtDetails
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<ArtDetailsListUiState>(ArtDetailsListUiState.Idle)
    val uiState: StateFlow<ArtDetailsListUiState> = _uiState
    private var loadingJob: Job? = null

    fun getData(artId: Int) {
        _uiState.value = ArtDetailsListUiState.Loading
        loadingJob?.cancel()
        loadingJob = viewModelScope.launch {
            val newUiState = when (val artDetailsResult =
                getArtDetails.invoke(artId)) {
                is Resource.Success -> {
                    ArtDetailsListUiState.Success(artDetailsResult.data)
                }
                is Resource.Error -> {
                    ArtDetailsListUiState.Error(artDetailsResult.error)
                }
            }
            _uiState.update { newUiState }
        }
    }
}