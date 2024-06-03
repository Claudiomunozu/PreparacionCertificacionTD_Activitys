package com.example.preparacioncertificaciontdactivitys.domain

import com.example.preparacioncertificaciontdactivitys.data.repository.VideoGameImpl
import com.example.preparacioncertificaciontdactivitys.data.response.VideoGameDetailResponse
import com.example.preparacioncertificaciontdactivitys.data.response.VideoGameResponse

class VideoGameUseCase (private val repository: VideoGameImpl) {
    suspend fun getAllVideoGamesInStock(): MutableList<VideoGameResponse> {
        return repository.fetchVideoGames()
    }

    suspend fun getVideoGameByIdOnStock(idVideoGame: Long): VideoGameDetailResponse {
        return repository.fetchVideoGameById(idVideoGame)
    }

    suspend fun saveAllVideoGamesOnStock(videoGames: MutableList<VideoGameResponse>) {
       return repository.saveAllVideoGamesDB(videoGames)
    }

    suspend fun getAllVideoGamesFromDB(): MutableList<VideoGameResponse> {
        return repository.getVideoGamesDB()
    }

    suspend fun saveDetailVideoGameOnDB(videoGameDetail: VideoGameDetailResponse){
        return repository.saveDetailVideoGameDB(videoGameDetail)
    }

    suspend fun getDetailVideoGameFromDB(idVideoGame: Long): VideoGameDetailResponse {
        return repository.getDetailVideoGameDB(idVideoGame)
    }
}