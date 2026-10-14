package com.nicolas.f2p_games.data

import android.R
import com.google.gson.annotations.SerializedName
import okhttp3.internal.platform.Platform

data class Game(
    @SerializedName("id")val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("short_description") val shortDescription: String,
    @SerializedName("platform") val platform: String,
    @SerializedName("genre") val genre: String
)
