package com.example.appollorate

import com.example.appollorate.fake.FakeLoginData
import com.example.appollorate.fake.FakeLoginRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class LoginTests {

    @Test
    fun SuccessfullLoginReturnsToken() = runTest {
        val loginRequest = FakeLoginData.loginRequest
        val repository = FakeLoginRepository()
        val expectedResult = "sometoken"
        val actualResult = repository.login(loginRequest)
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun UnsuccessfullLoginReturnsErrorMessage() {
    }
}
