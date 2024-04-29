package com.danamon.remote.base

import com.danamon.data.base.ResponseEntity

data class BaseResponse<REMOTE, ENTITY>(
    val status: Int? = null,
    val message: String? = null,
    val data: REMOTE? = null
) {
    fun toEntityWithData(dataMapper: (REMOTE?) -> ENTITY?): ResponseEntity<ENTITY> {
        return ResponseEntity(
            status = status,
            message = message,
            data = dataMapper(data)
        )
    }
}