package com.github.atsumerudev.api.metron.model.info

import com.github.atsumerudev.api.metron.util.itemOrEmpty
import java.io.Serializable

abstract class BaseResult : Serializable {
    var id = 0

    var name: String? = null
        get() = itemOrEmpty(field)

    var modified: String? = null
        get() = itemOrEmpty(field)
}
