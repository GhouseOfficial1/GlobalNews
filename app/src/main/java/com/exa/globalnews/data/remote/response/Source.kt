package com.exa.globalnews.data.remote.response


import androidx.room.TypeConverters
import com.exa.globalnews.models.SourcesConverter
import com.google.gson.annotations.SerializedName

@TypeConverters(SourcesConverter::class)
data class Source(
    @SerializedName("id")
    var id: Any?,
    @SerializedName("name")
    var name: String?
)