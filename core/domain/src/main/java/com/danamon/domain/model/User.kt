package com.danamon.domain.model

import com.danamon.common.ext.emptyInt
import com.danamon.common.ext.emptyString

class User(
    var id: Int? = emptyInt(),
    var username: String? = emptyString(),
    var email: String? = emptyString(),
    var role: String? = emptyString()
)