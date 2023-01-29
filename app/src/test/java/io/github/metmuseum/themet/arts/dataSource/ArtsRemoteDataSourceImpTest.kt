package io.github.metmuseum.themet.arts.dataSource

import com.squareup.moshi.Moshi
import io.github.metmuseum.themet.arts.api.ArtsServiceAPI
import io.github.metmuseum.themet.arts.artDetails1
import io.github.metmuseum.themet.arts.artIdList1
import io.github.metmuseum.themet.common.network.Exceptions
import io.github.metmuseum.themet.common.network.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
class ArtsRemoteDataSourceImpTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiClient: ArtsServiceAPI

    private var client = OkHttpClient.Builder().build()

    private val moshi: Moshi = Moshi.Builder().build()

    @Before
    fun createServer() {
        mockWebServer = MockWebServer()

        apiClient = Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .baseUrl(mockWebServer.url("/")) // setting a dummy url
            .build()
            .create(ArtsServiceAPI::class.java)
    }

    @After
    fun shutdownServer() {
        mockWebServer.shutdown()
    }

    @Test
    fun `correct art id list response is parsed into success result`() = runTest {

        val response = MockResponse()
            .setBody(successfulArtIdListResponse)
            .setResponseCode(200)

        mockWebServer.enqueue(response)

        val artsRemoteDataSource = ArtsRemoteDataSourceImp(apiClient)
        val expectedArtIdList = artIdList1

        val result =
            artsRemoteDataSource.getArtIdList(searchKey = "Sunflower", true)

        assert(result is Resource.Success)
        assertEquals((result as Resource.Success).data, expectedArtIdList)

    }

    @Test
    fun `correct art details response is parsed into success result`() = runTest {

        val response = MockResponse()
            .setBody(successfulArtDetailsListResponse)
            .setResponseCode(200)

        mockWebServer.enqueue(response)

        val artsRemoteDataSource = ArtsRemoteDataSourceImp(apiClient)
        val expectedArtDetails = artDetails1

        val result =
            artsRemoteDataSource.getArtDetails(12342)
        assert(result is Resource.Success)
        assertEquals((result as Resource.Success).data, expectedArtDetails)

    }

    @Test
    fun `malformed response returns json error result`() = runTest {

        val response = MockResponse()
            .setBody(errorResponse)
            .setResponseCode(200)

        mockWebServer.enqueue(response)

        val artsRemoteDataSource = ArtsRemoteDataSourceImp(apiClient)

        val result =
            artsRemoteDataSource.getArtIdList(searchKey = "Sunflower", true)

        assert(result is Resource.Error)
        assert((result as Resource.Error).error is Exceptions)
    }

}