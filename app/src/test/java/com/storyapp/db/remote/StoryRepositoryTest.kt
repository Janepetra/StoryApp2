package com.storyapp.db.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.storyapp.utils.DummyData.generateDummyRequestLogin
import com.storyapp.utils.DummyData.generateDummyResponseLogin
import com.storyapp.response.Login
import com.storyapp.utils.MainDispatcherRule
import com.storyapp.utils.getOrAwaitValue
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.io.File

class StoryRepositoryTest {


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: StoryRepository

    @Mock
    private var mockFile = File("fileName")

    @Before
    fun setUp() {
        repository = Mockito.mock(StoryRepository::class.java)
    }

//    @Test
//    fun `verify getResponseLogin function is working`() {
//        val dummyRequestLogin = generateDummyRequestLogin()
//        val dummyResponseLogin = generateDummyResponseLogin()
//
//        val expectedResponseLogin = MutableLiveData<Login>()
//        expectedResponseLogin.value = dummyResponseLogin
//
//        repository.getLogin(dummyRequestLogin)
//
//        Mockito.verify(repository).getLogin(dummyRequestLogin)
//        `when`(repository.login).thenReturn(expectedResponseLogin)
//
//        val actualData = repository.login.getOrAwaitValue()
//        Mockito.verify(repository).login
//        Assert.assertNotNull(actualData)
//        assertEquals(expectedResponseLogin.value, actualData)
//    }
}
