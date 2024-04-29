package com.danamon.remote

import com.danamon.common.ext.emptyString
import com.danamon.remote.base.BaseResponse
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BaseResponseTest {
    private var jsonString = emptyString()

    @Before
    fun setUp() {
        jsonString = """
        {
            "message": "Title",
            "status": 200,
            "data": null
        }
        """.trimIndent()
    }

    @Test
    fun `should be parse data from json string`() {
        val model = Gson().fromJson(jsonString, BaseResponse::class.java)
        assertEquals(model.message, "Title")
        assertEquals(model.status, 200)
    }

    @Test
    fun `should be map data to entity`() {
        val model = Gson().fromJson(jsonString, BaseResponse::class.java)
        val entity = model.toEntityWithData { null }
        assertEquals(entity.message, "Title")
        assertEquals(entity.status, 200)
        Assert.assertNull(entity.data)
    }
}