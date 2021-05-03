package com.ashton.mygreenday

import com.ashton.mygreenday.retrofit.RetrofitClient
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun describe_json() {
        val searchResults = RetrofitClient.service.search().execute()

        searchResults.body()?.list?.forEach { item -> println(item) }

        assert(searchResults.isSuccessful)
    }
}