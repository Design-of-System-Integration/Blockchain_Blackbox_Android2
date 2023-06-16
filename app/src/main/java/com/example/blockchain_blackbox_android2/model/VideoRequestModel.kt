package com.example.blockchain_blackbox_android2.model

import com.google.gson.annotations.SerializedName

data class VideoRequestResponse(
    @SerializedName("message") val message: String
)

data class VideoRequestRequest(
    @SerializedName("title") val name:String,
    @SerializedName("date") val email:String,
    @SerializedName("latitude") val latitude:Double,
    @SerializedName("longitude") val longitude:String,
    @SerializedName("detail") val detail:String,
    @SerializedName("reward") val reward:Int,
)
