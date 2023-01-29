package io.github.metmuseum.themet.arts.ui.list

import app.cash.turbine.test
import com.google.common.truth.Truth
import io.github.metmuseum.themet.arts.artIdList1
import io.github.metmuseum.themet.arts.fake.usecase.FakeGetArtIdList
import io.github.metmuseum.themet.common.network.Exceptions
import io.github.metmuseum.themet.util.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class ArtIdListViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private lateinit var viewModel: ArtIdListViewModel

    /*
     * In this scenario, we want to test that after creating the viewModel the initial value of the uiState
     * is ArtIdListUiState.Idle.
     */
    @Test
    fun `creating a viewModel exposes Idle ui state`() = runTest {

        // when
        viewModel = ArtIdListViewModel(
            getArtIdList = FakeGetArtIdList()
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
        viewModel = ArtIdListViewModel(
            getArtIdList = FakeGetArtIdList()
        )

        var currentState = viewModel.uiState.value

        // then
        viewModel.uiState.test {
            Truth.assertThat(awaitItem()).isEqualTo(currentState)
        }

        // when
        viewModel.getData("Sunflower")

        // then
        viewModel.uiState.test {
            currentState = ArtIdListUiState.Loading
            Truth.assertThat(awaitItem()).isEqualTo(currentState)

            currentState = ArtIdListUiState.Success(artIdList1)
            Truth.assertThat(awaitItem()).isEqualTo(currentState)
        }
    }

    /*
     * In this scenario, we want to test that after creating the viewModel and make a failure request
     */
    @Test
    fun `creating a viewModel updates ui state to failure after loading`() = runTest {

        // when
        viewModel = ArtIdListViewModel(
            getArtIdList = FakeGetArtIdList(isSuccessful = false)
        )

        var currentState = viewModel.uiState.value

        // then
        viewModel.uiState.test {
            Truth.assertThat(awaitItem()).isEqualTo(currentState)
        }

        // when
        viewModel.getData("Sunflower")

        // then
        viewModel.uiState.test {
            currentState = ArtIdListUiState.Loading
            Truth.assertThat(awaitItem()).isEqualTo(currentState)

            currentState = ArtIdListUiState.Error(Exceptions.RemoteDataSourceException("Boom..."))
            Truth.assertThat(awaitItem()).isEqualTo(currentState)
        }
    }

    /*
     * In this scenario, we want to test that after creating the viewModel and check failure status request when
     * our searchKey less than 2 char.
     */
    @Test
    fun `creating a viewModel update ui state to failure when our searchKey less than 2 char`() =
        runTest {

            //when
            viewModel = ArtIdListViewModel(
                getArtIdList = FakeGetArtIdList(isSuccessful = false)
            )

            // then
            var currentState = viewModel.uiState.value

            // then
            viewModel.uiState.test {
                Truth.assertThat(awaitItem()).isEqualTo(currentState)
            }

            // when
            viewModel.getData("a")

            // then
            viewModel.uiState.test {
                currentState = ArtIdListUiState.Loading
                Truth.assertThat(awaitItem()).isEqualTo(currentState)

                currentState =
                    ArtIdListUiState.Error(Exceptions.RemoteDataSourceException("Boom..."))
                Truth.assertThat(awaitItem()).isEqualTo(currentState)
            }
        }

    /*
     * In this scenario, we want to test that after creating the viewModel and does not make request when
     * our searchKey like the latest searchKey.
     */
    @Test
    fun `creating a viewModel update ui state to idle when our searchKey does not change`() =
        runTest {

            //when
            viewModel = ArtIdListViewModel(
                getArtIdList = FakeGetArtIdList()
            )

            // then
            val currentState = viewModel.uiState.value

            // when
            viewModel.getData("")

            // then
            viewModel.uiState.test {
                Truth.assertThat(awaitItem()).isEqualTo(currentState)
            }
        }

    /*
    * In this scenario, we want to test the value of latestSearchKey variable when set correct
    */
    @Test
    fun `set latest search key with valid input, returns success`() = runTest {

        // when
        viewModel = ArtIdListViewModel(
            getArtIdList = FakeGetArtIdList()
        )

        // then
        viewModel.setLatestSearchKey("Sunflower")
        Truth.assertThat(viewModel.getLatestSearchKey() == "Sunflower")

    }

    /*
     * In this scenario, we want to test the value of latestSearchKey variable when set wrong
     */
    @Test
    fun `set latest search key with invalid input, returns error`() = runTest {

        // when
        viewModel = ArtIdListViewModel(
            getArtIdList = FakeGetArtIdList()
        )

        // then
        viewModel.setLatestSearchKey("Sunflower")
        Truth.assertThat(viewModel.getLatestSearchKey() != "Sun")

    }

}