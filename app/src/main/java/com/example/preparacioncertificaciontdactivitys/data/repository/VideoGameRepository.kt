package com.example.preparacioncertificaciontdactivitys.data.repository


import com.example.preparacioncertificaciontdactivitys.data.response.VideoGameDetailResponse
import com.example.preparacioncertificaciontdactivitys.data.response.VideoGameResponse

interface VideoGameRepository {
    suspend fun fetchVideoGames(): MutableList<VideoGameResponse>
    suspend fun fetchVideoGameById(idVideoGameService: Long) : VideoGameDetailResponse
    suspend fun saveAllVideoGamesDB(videoGames: MutableList<VideoGameResponse>)
    suspend fun getVideoGamesDB(): MutableList<VideoGameResponse>
    suspend fun saveDetailVideoGameDB(videoGameDetails: VideoGameDetailResponse)
    suspend fun getDetailVideoGameDB(idVideoGame: Long): VideoGameDetailResponse
}