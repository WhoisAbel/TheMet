package io.github.metmuseum.themet.arts.ui.details

import app.cash.turbine.test
import com.google.common.truth.Truth
import io.github.metmuseum.themet.arts.artDetails1
import io.github.metmuseum.themet.arts.fake.usecase.FakeGetArtDetails
import io.github.metmuseum.themet.common.network.Exceptions
import io.github.metmuseum.themet.util.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtDetailsViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private lateinit var viewModel: ArtDetailsViewModel

    /*
     * In this scenario, we want to test that after creating the viewModel the initial value of the uiState
     * is ArtIdListUiState.Idle.
     */
    @Test
    fun `creating a viewModel exposes Idle ui state`() = runTest {

        // when
        viewModel = ArtDetailsViewModel(
            getArtDetails = FakeGetArtDetails()
        )

        val currentState = viewModel.uiState.value

        // then
        viewModel.uiState.test {
            Truth.assertThat(awaitItem()).isEqualTo(currentState)
        }
    }

    /*
     * In this scenario, we want to test that after creating the viewModel and make a successful request
     */
    @Test
    fun `creating a viewModel updates ui state to success after loading`() = runTest {

        // when
        viewModel = ArtDetailsViewModel(
            getArtDetails = FakeGetArtDetails()
        )

        var currentState = viewModel.uiState.value

        // then
        viewModel.uiState.test {
            Truth.assertThat(awaitItem()).isEqualTo(currentState)
        }

        // when
        viewModel.getData(23543)

        // then
        viewModel.uiState.test {
            currentState = ArtDetailsListUiState.Loading
            Truth.assertThat(awaitItem()).isEqualTo(currentState)

            currentState = ArtDetailsListUiState.Success(artDetails = artDetails1)
            Truth.assertThat(awaitItem()).isEqualTo(currentState)
        }
    }

    /*
     * In this scenario, we want to test that after creating the viewModel and make a failure request
     */
    @Test
    fun `creating a viewModel updates ui state to failure after loading`() = runTest {

        // when
        viewModel = ArtDetailsViewModel(
            getArtDetails = FakeGetArtDetails(isSuccessful = false)
        )

        var currentState = viewModel.uiState.value

        // then
        viewModel.uiState.test {
            Truth.assertThat(awaitItem()).isEqualTo(currentState)
        }

        // when
        viewModel.getData(0)

        // then
        viewModel.uiState.test {
            currentState = ArtDetailsListUiState.Loading
            Truth.assertThat(awaitItem()).isEqualTo(currentState)

            currentState =
                ArtDetailsListUiState.Error(Exceptions.RemoteDataSourceException("Boom..."))
            Truth.assertThat(awaitItem()).isEqualTo(currentState)
        }
    }

}