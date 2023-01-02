package com.github.atsumerudev.api.metron.model.short

import com.github.atsumerudev.api.metron.util.itemOrEmpty
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class ShortIssue : Serializable {
    var id = 0

    var issue: String? = null
        get() = itemOrEmpty(field)

    @SerializedName("cover_date")
    var coverDate: Date? = null
        get() = if (field == null) Date() else field

    var modified: String? = null
        get() = itemOrEmpty(field)
}
