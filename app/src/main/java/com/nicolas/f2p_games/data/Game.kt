package com.nicolas.f2p_games.data

import android.webkit.WebStorage
import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("id")val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("short_description") val shortDescription: String,
    @SerializedName("description") val description: String?,
    @SerializedName("developer") val developer: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("platform") val platform: String,
    @SerializedName("freetogame_profile_url") val profileUrl: String,
    @SerializedName("game_url") val gameUrl: String,
    @SerializedName("genre") val genre: String,
    @SerializedName("screenshots")val screenshots: List<Screenshot>?,
    @SerializedName("minimum_system_requirements")val systemRequirements: SystemRequirements?


    )

data class Screenshot (
    @SerializedName("image")val image: String,
    )

data class SystemRequirements(
    @SerializedName("os")val os: String,
    @SerializedName("processor")val processor: String,
    @SerializedName("memory")val memory: String,
    @SerializedName("graphics")val graphics: String,
    @SerializedName("storage")val storage: String,
    )