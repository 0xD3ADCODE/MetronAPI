package com.github.atsumerudev.api.metron.model.short

import com.github.atsumerudev.api.metron.util.itemOrEmpty
import java.io.Serializable

class ShortSerie : Serializable {
    var id = 0

    var series: String? = null
        get() = itemOrEmpty(field)

    var modified: String? = null
        get() = itemOrEmpty(field)
}
