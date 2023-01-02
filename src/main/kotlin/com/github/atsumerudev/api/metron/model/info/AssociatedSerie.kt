package com.github.atsumerudev.api.metron.model.info

import com.github.atsumerudev.api.metron.util.itemOrEmpty
import java.io.Serializable

class AssociatedSerie : Serializable {
    var id = 0

    var series: String? = null
        get() = itemOrEmpty(field)
}