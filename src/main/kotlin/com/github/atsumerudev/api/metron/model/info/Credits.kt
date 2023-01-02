package com.github.atsumerudev.api.metron.model.info

import com.github.atsumerudev.api.metron.util.itemOrEmpty
import java.io.Serializable

class Credits : Serializable {
    var id = 0

    var creator: String? = null
        get() = itemOrEmpty(field)

    var role: List<Role>? = null
        get() = if (field == null) ArrayList() else field
}