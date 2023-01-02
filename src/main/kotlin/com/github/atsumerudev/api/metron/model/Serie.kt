package com.github.atsumerudev.api.metron.model

import com.github.atsumerudev.api.metron.model.info.AssociatedSerie
import com.github.atsumerudev.api.metron.model.info.Genre
import com.github.atsumerudev.api.metron.model.info.Publisher
import com.github.atsumerudev.api.metron.model.info.SerieType
import com.github.atsumerudev.api.metron.util.itemOrEmpty
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Serie : Serializable {
    var id = 0

    var name: String? = null
        get() = itemOrEmpty(field)

    @SerializedName("sort_name")
    var sortName: String? = null
        get() = itemOrEmpty(field)

    var volume = 0

    @SerializedName("series_type")
    var seriesType: SerieType? = null
        get() = if (field == null) SerieType() else field

    var publisher: Publisher? = null
        get() = if (field == null) Publisher() else field

    @SerializedName("year_began")
    var yearBegan = 0

    @SerializedName("year_end")
    var yearEnd = 0

    @SerializedName("desc")
    var description: String? = null
        get() = itemOrEmpty(field)

    @SerializedName("issue_count")
    var issueCount = 0

    var image: String? = null
        get() = itemOrEmpty(field)

    var genres: List<Genre>? = null
        get() = if (field == null) ArrayList() else field

    var associated: List<AssociatedSerie>? = null
        get() = if (field == null) ArrayList() else field

    @SerializedName("resource_url")
    var resourceUrl: String? = null
        get() = itemOrEmpty(field)

    var modified: String? = null
        get() = itemOrEmpty(field)
}
