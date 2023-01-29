package io.github.metmuseum.themet.arts.ui.details

import io.github.metmuseum.themet.arts.artDetails2
import io.github.metmuseum.themet.arts.usecase.ArtsRepository
import io.github.metmuseum.themet.arts.usecase.GetArtDetailsImp
import io.github.metmuseum.themet.common.network.Resource
import io.github.metmuseum.themet.util.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class ArtDetailsViewModelIntegrationTest {

    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)

    @get:Rule
    val coroutineRule = CoroutineRule(testDispatcher)

    @Test
    fun `calling getDate triggers the api client`() = runTest {

        val repository: ArtsRepository = mock()
        whenever(repository.getArtDetails(any())).thenReturn(Resource.Success(artDetails2))
        val getArtDetails = GetArtDetailsImp(repository)

        val viewModel = ArtDetailsViewModel(
            getArtDetails = getArtDetails
        )

        viewModel.getData(43234)

        runCurrent()

        verify(repository, times(1)).getArtDetails(any())

    }


}