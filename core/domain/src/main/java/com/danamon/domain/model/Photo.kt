package com.danamon.domain.model

import com.danamon.common.ext.emptyInt
import com.danamon.common.ext.emptyString

data class Photo(
    var albumId: Int = emptyInt(),
    var id: Int = emptyInt(),
    var title: String = emptyString(),
    var url: String = emptyString(),
    var thumbnailUrl: String = emptyString()
)