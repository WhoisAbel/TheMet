package io.github.metmuseum.themet.arts.ui.list

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.metmuseum.themet.arts.usecase.GetArtIdList
import io.github.metmuseum.themet.common.network.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtIdListViewModel @Inject constructor(
    private val getArtIdList: GetArtIdList
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<ArtIdListUiState>(ArtIdListUiState.Idle)
    val uiState: StateFlow<ArtIdListUiState> = _uiState
    private var loadingJob: Job? = null
    private var latestSearchKey: String = ""
    private var keyboardVisibility: Boolean = true

    fun getData(searchKey: String) {
        if (searchKey != latestSearchKey) {
            _uiState.value = ArtIdListUiState.Loading
            loadingJob?.cancel()
            loadingJob = viewModelScope.launch {
                val newUiState = when (val artIdListResult =
                    getArtIdList.invoke(searchKey, true)) {
                    is Resource.Success -> {
                        ArtIdListUiState.Success(artIdListResult.data)
                    }
                    is Resource.Error -> {
                        ArtIdListUiState.Error(artIdListResult.error)
                    }
                }
                _uiState.update { newUiState }
            }
        }
    }


    fun setLatestSearchKey(searchKey: String) {
        this.latestSearchKey = searchKey
    }

    fun getLatestSearchKey(): String {
        return latestSearchKey
    }

    fun setKeyboardVisibility(status: Boolean) {
        keyboardVisibility = status
    }

    fun getKeyboardVisibility(): Boolean = keyboardVisibility
}