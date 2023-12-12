package com.storyapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.storyapp.utils.MainDispatcherRule
import com.storyapp.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import java.io.File

class DetailStoryViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: DetailStoryViewModel

    @Mock
    private var mockFile = File("fileName")

    @Before
    fun setUp() {
        viewModel = Mockito.mock(viewModel::class.java)
    }

    // upload
    @Test
    fun `when message upload should return the right data and not null`() {
        val expectedRegisterMessage = MutableLiveData<String>()
        expectedRegisterMessage.value = "Story Uploaded"

        Mockito.`when`(viewModel.messageLogin).thenReturn(expectedRegisterMessage)

        val actualRegisterMessage = viewModel.messageLogin.getOrAwaitValue()

        Mockito.verify(viewModel).messageLogin
        Assert.assertNotNull(actualRegisterMessage)
        Assert.assertEquals(expectedRegisterMessage.value, actualRegisterMessage)
    }
}