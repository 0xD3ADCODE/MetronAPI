package com.github.atsumerudev.api.metron.model

import com.github.atsumerudev.api.metron.model.info.*
import com.github.atsumerudev.api.metron.util.itemOrEmpty
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class Issue : Serializable {
    var id = 0

    var publisher: Publisher? = null
        get() = if (field == null) Publisher() else field

    var series: Series? = null
        get() = if (field == null) Series() else field

    var number: String? = null
        get() = itemOrEmpty(field)

    var title: String? = null
        get() = itemOrEmpty(field)

    var name: List<String>? = null
        get() = if (field == null) ArrayList() else field

    @SerializedName("cover_date")
    var coverDate: Date? = null
        get() = if (field == null) Date() else field

    @SerializedName("store_date")
    var storeDate: Date? = null
        get() = if (field == null) Date() else field

    var price: String? = null
        get() = itemOrEmpty(field)

    var rating: Rating? = null
        get() = if (field == null) Rating() else field

    var sku: String? = null
        get() = itemOrEmpty(field)

    var isbn: String? = null
        get() = itemOrEmpty(field)

    var upc: String? = null
        get() = itemOrEmpty(field)

    var page = 0

    var desc: String? = null
        get() = itemOrEmpty(field)

    var image: String? = null
        get() = itemOrEmpty(field)

    var arcs: List<String>? = null
        get() = if (field == null) ArrayList() else field

    var credits: List<Credits>? = null
        get() = if (field == null) ArrayList() else field

    var characters: List<Character>? = null
        get() = if (field == null) ArrayList() else field

    var teams: List<Team>? = null
        get() = if (field == null) ArrayList() else field

    var reprints: List<String>? = null
        get() = if (field == null) ArrayList() else field

    var variants: List<Reprint>? = null
        get() = if (field == null) ArrayList() else field

    @SerializedName("resource_url")
    var resourceUrl: String? = null
        get() = itemOrEmpty(field)

    var modified: String? = null
        get() = itemOrEmpty(field)
}
