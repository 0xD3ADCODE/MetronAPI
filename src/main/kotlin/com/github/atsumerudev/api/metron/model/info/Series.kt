package com.github.atsumerudev.api.metron.model.info

import com.github.atsumerudev.api.metron.util.itemOrEmpty
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Series : Serializable {
    var id = 0

    var name: String? = null
        get() = itemOrEmpty(field)

    @SerializedName("sort_name")
    var sortName: String? = null
        get() = itemOrEmpty(field)

    var volume: String? = null
        get() = itemOrEmpty(field)

    @SerializedName("series_type")
    var seriesType: SerieType? = null
        get() = if (field == null) SerieType() else field

    var genres: List<Genre>? = null
        get() = if (field == null) ArrayList() else field
}