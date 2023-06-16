package com.example.blockchain_blackbox_android2.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface VideoService {
    @POST("")
    fun postVideo(
        @Body jsonParams: VideoRequestRequest
    ): Call<VideoRequestResponse>

    companion object{
        fun retrofitPostVideo(jsonParams: VideoRequestRequest): Call<VideoRequestResponse>{
            return ApiClient.create(VideoService::class.java).postVideo(jsonParams)
        }
    }
}