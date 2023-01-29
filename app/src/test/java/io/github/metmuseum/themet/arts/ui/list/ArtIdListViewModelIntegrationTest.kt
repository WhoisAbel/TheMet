package io.github.metmuseum.themet.arts.ui.list

import io.github.metmuseum.themet.arts.artIdList1
import io.github.metmuseum.themet.arts.usecase.ArtsRepository
import io.github.metmuseum.themet.arts.usecase.GetArtIdListImp
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
class ArtIdListViewModelIntegrationTest {

    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)

    @get:Rule
    val coroutineRule = CoroutineRule(testDispatcher)

    @Test
    fun `calling getDate triggers the api client`() = runTest {

        val repository: ArtsRepository = mock()
        whenever(repository.getArtIdList(any(), any())).thenReturn(Resource.Success(artIdList1))
        val getArtIdList = GetArtIdListImp(repository)

        val viewModel = ArtIdListViewModel(
            getArtIdList = getArtIdList
        )

        viewModel.getData("Sunflower")

        runCurrent()

        verify(repository, times(1)).getArtIdList(any(), any())
    }


}