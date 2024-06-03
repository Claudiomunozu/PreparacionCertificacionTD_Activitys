package com.example.preparacioncertificaciontdactivitys.data.network.api

import com.example.preparacioncertificaciontdactivitys.data.network.retrofit.RetrofitHelper
import com.example.preparacioncertificaciontdactivitys.data.response.VideoGameResponse

class VideoGameApiClient {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getVideoGames() : MutableList<VideoGameResponse> {
        val response = retrofit.create(VideoGameService::class.java).getAllVideoGames()
        return response
    }
}