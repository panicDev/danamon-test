package com.danamon.domain

import com.danamon.domain.datastate.DataState
import com.danamon.domain.datastate.StateMessage
import com.danamon.domain.model.PhotoList
import com.danamon.domain.repository.PhotoRepository
import com.danamon.domain.usecase.PhotoUseCase
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PhotoUseCaseTest {
    private val repository: PhotoRepository = mockk()
    private val underUnitTest = PhotoUseCase(repository)
    private val responseValidation = PhotoList()
    private val errorStateMessage = StateMessage(
        status = 500,
        message = "Error Message"
    )

    private val itemValidate = DataState.SUCCESS(responseValidation)
    private val itemError    = DataState.ERROR<PhotoList>(stateMessage = errorStateMessage)

    @Before
    fun setUp(){
        clearAllMocks()
    }

    @Test
    fun `should get api response success`() = runBlocking {
        coEvery { repository.getPhotos() } returns itemValidate
        val apiResponse = underUnitTest.invoke()
        Assert.assertEquals(apiResponse, itemValidate)
    }

    @Test
    fun `should get api response error`() = runBlocking {
        coEvery { repository.getPhotos() } returns itemError
        val apiResponse = underUnitTest.invoke()
        Assert.assertEquals(apiResponse, itemError)
    }
}