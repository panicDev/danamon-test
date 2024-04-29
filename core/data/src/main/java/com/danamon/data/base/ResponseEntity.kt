package com.danamon.data.base

data class ResponseEntity<T>(
    var status: Int? = null,
    var message: String? = null,
    var data: T? = null
) {
    companion object {
        const val SUCCESS_RESPONSE_CODE = 200
    }
}