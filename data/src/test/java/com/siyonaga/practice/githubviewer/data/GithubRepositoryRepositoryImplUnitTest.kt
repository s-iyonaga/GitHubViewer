package com.siyonaga.practice.githubviewer.data

import com.siyonaga.practice.githubviewer.model.GithubRepositoryDetail
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class GithubRepositoryRepositoryImplUnitTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var retrofitJson: Json
    private lateinit var mockGithubRepositoryDetailBody: String
    private lateinit var githubRepositoryRepositoryImpl: GithubRepositoryRepositoryImpl
    private lateinit var githubRepositoryApiService: GithubRepositoryApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        retrofitJson = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
        mockGithubRepositoryDetailBody = """
            {
                "content": "44GC44GE44GG44GI44GK"
            }
        """.trimIndent()

        mockWebServer.start()

        githubRepositoryApiService = Retrofit
            .Builder()
            .addConverterFactory(retrofitJson.asConverterFactory("application/json".toMediaType()))
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(GithubRepositoryApiService::class.java)

        githubRepositoryRepositoryImpl = GithubRepositoryRepositoryImpl(
            githubRepositoryApiService = githubRepositoryApiService,
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `API 呼び出しで GithubRepositoryDetail を取得できた場合に、期待した GithubRepositoryDetail を返す`() {
        runTest {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(200)
                    .setBody(mockGithubRepositoryDetailBody),
            )

            val ownerName = "developer001"
            val repoName = "android001"
            val readmeText = "あいうえお"
            val actualGithubRepositoryDetail = githubRepositoryRepositoryImpl
                .getGithubRepositoryDetail(
                    ownerName = ownerName,
                    repoName = repoName,
                )
            val expectedGithubRepositoryDetail = GithubRepositoryDetail(
                readmeText = readmeText,
            )

            assertEquals(
                expectedGithubRepositoryDetail,
                actualGithubRepositoryDetail,
            )
        }
    }
}
