package org.isoron.uhabits.core.tasks

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class HabitMockWebServerTest {
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetHabits() {
        val mockResponse = MockResponse()
            .setBody("""[{"id": 1, "name": "Test Habit"}]""")
            .setResponseCode(200)
        mockWebServer.enqueue(mockResponse)

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(mockWebServer.url("/habit"))
            .build()
        val response = client.newCall(request).execute()
        assertTrue(response.isSuccessful)
        assertTrue(response.body?.string() == """[{"id": 1, "name": "Test Habit"}]""")
    }

    @Test
    fun testAddHabit() {
        val mockResponse = MockResponse()
            .setBody("""{"id": 1, "name": "New Habit"}""")
            .setResponseCode(201)
        mockWebServer.enqueue(mockResponse)
        val client = OkHttpClient()
        val requestBody = """{"name": "New Habit"}""".toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url(mockWebServer.url("/habit"))
            .post(requestBody)
            .build()
        val response = client.newCall(request).execute()
        assertTrue(response.isSuccessful)
        assertTrue(response.body?.string() == """{"id": 1, "name": "New Habit"}""")
    }

    @Test
    fun testDeleteHabit() {
        val mockResponse = MockResponse()
            .setResponseCode(204)
        mockWebServer.enqueue(mockResponse)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(mockWebServer.url("/habit/1"))
            .delete()
            .build()
        val response = client.newCall(request).execute()
        assertTrue(response.isSuccessful)
    }
}