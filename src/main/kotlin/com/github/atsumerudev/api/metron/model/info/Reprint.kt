package com.github.atsumerudev.api.metron.model.info

import com.github.atsumerudev.api.metron.util.itemOrEmpty
import java.io.Serializable

class Reprint : Serializable {
    var id = 0

    var issue: String? = null
        get() = itemOrEmpty(field)
}