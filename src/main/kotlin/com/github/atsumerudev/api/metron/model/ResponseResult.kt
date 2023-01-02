package com.github.atsumerudev.api.metron.model

import com.github.atsumerudev.api.metron.util.itemOrEmpty
import java.io.Serializable

class ResponseResult<T> : Serializable {
    var count = 0

    var next: String? = null
        get() = itemOrEmpty(field)

    var previous: String? = null
        get() = itemOrEmpty(field)

    var results: List<T>? = null
        get() = if (field == null) ArrayList() else field
}
