package com.danamon.remote

import com.danamon.common.ext.emptyString
import com.danamon.remote.model.RemotePhotoList
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RemotePhotoListTest {

    private var jsonString = emptyString()

    @Before
    fun setUp(){
        jsonString = """
        {
            [
                {
                    "albumId": 1,
                    "id": "1",
                    "title": "ccusamus beatae ad facilis cum similique qui sunt",
                    "url": "https://via.placeholder.com/600/92c952",
                    "thumbnailUrl": "https://via.placeholder.com/150/92c952"
                }
            ]
        }
        """.trimIndent()
    }

    @Test
    fun `should be parse data from json string`() {
        val model = Gson().fromJson(jsonString, RemotePhotoList::class.java)
        Assert.assertNotNull(model.data)
        Assert.assertEquals(model.data?.first()?.albumId, 1)
        Assert.assertEquals(model.data?.first()?.id, 1)
        Assert.assertEquals(
            model.data?.first()?.title,
            "ccusamus beatae ad facilis cum similique qui sunt"
        )
        Assert.assertEquals(model.data?.first()?.url, "https://via.placeholder.com/600/92c952")
        Assert.assertEquals(model.data?.first()?.thumbnailUrl, "https://via.placeholder.com/150/92c952")
    }

    @Test
    fun `should be map data to entity`() {
        val model  = Gson().fromJson(jsonString, RemotePhotoList::class.java)
        val entity = model.toEntity()
        Assert.assertNotNull(entity.data)
        Assert.assertEquals(entity.data?.first()?.albumId, 1)
        Assert.assertEquals(entity.data?.first()?.id, 1)
        Assert.assertEquals(
            entity.data?.first()?.title,
            "ccusamus beatae ad facilis cum similique qui sunt"
        )
        Assert.assertEquals(entity.data?.first()?.url, "https://via.placeholder.com/600/92c952")
        Assert.assertEquals(entity.data?.first()?.thumbnailUrl, "https://via.placeholder.com/150/92c952")
    }

    @Test
    fun `should be map data from entity`() {
        val model    = Gson().fromJson(jsonString, RemotePhotoList::class.java)
        val entity   = model.toEntity()
        val newModel = RemotePhotoList.fromEntity(entity)
        Assert.assertNotNull(newModel.data)
        Assert.assertEquals(newModel.data?.first()?.albumId, 1)
        Assert.assertEquals(newModel.data?.first()?.id, 1)
        Assert.assertEquals(
            newModel.data?.first()?.title,
            "ccusamus beatae ad facilis cum similique qui sunt"
        )
        Assert.assertEquals(newModel.data?.first()?.url, "https://via.placeholder.com/600/92c952")
        Assert.assertEquals(newModel.data?.first()?.thumbnailUrl, "https://via.placeholder.com/150/92c952")
    }

}