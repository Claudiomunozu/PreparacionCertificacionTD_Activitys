package com.example.preparacioncertificaciontdactivitys.data.repository

import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import com.example.preparacioncertificaciontdactivitys.data.local.dao.VideoGameDao
import com.example.preparacioncertificaciontdactivitys.data.network.api.VideoGameService
import com.example.preparacioncertificaciontdactivitys.data.response.VideoGameDetailResponse
import com.example.preparacioncertificaciontdactivitys.data.response.VideoGameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoGameImpl (
    private var apiservice: VideoGameService,
    private var daoDB: VideoGameDao
): VideoGameRepository{

    override suspend fun fetchVideoGames(): MutableList<VideoGameResponse> {

        return withContext(Dispatchers.IO){
            val listVideoGames = apiservice.getAllVideoGames()
            listVideoGames
        }
    }

    override suspend fun fetchVideoGameById(idVideoGameService: Long): VideoGameDetailResponse {
        return withContext(Dispatchers.IO){
            val videoGameDetail = apiservice.getVideoGameById(idVideoGameService)
            videoGameDetail
        }
    }

    override suspend fun saveAllVideoGamesDB(videoGames: MutableList<VideoGameResponse>) {
        return withContext(Dispatchers.IO){
            daoDB.insertVideoGames(videoGames)
        }
    }

    override suspend fun getVideoGamesDB(): MutableList<VideoGameResponse> {
        return withContext(Dispatchers.IO){
            val videoGamesBD = daoDB.getAllVideoGames()
            videoGamesBD
        }
    }

    override suspend fun saveDetailVideoGameDB(videoGameDetails: VideoGameDetailResponse) {
        return withContext(Dispatchers.IO){
            daoDB.insertVideoGameDetail(videoGameDetails)
            Log.d(TAG, "DBASE: ")
        }
    }

    override suspend fun getDetailVideoGameDB(idVideoGame: Long): VideoGameDetailResponse {
        return withContext(Dispatchers.IO){
            val videoGameDetailBD = daoDB.getVideoGameDetailById(idVideoGame)
            videoGameDetailBD
        }
    }
}