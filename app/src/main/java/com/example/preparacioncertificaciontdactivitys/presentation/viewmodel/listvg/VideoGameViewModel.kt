package com.example.preparacioncertificaciontdactivitys.presentation.viewmodel.listvg

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preparacioncertificaciontdactivitys.data.response.VideoGameResponse
import com.example.preparacioncertificaciontdactivitys.domain.VideoGameUseCase
import kotlinx.coroutines.launch

class VideoGameViewModel(private val useCase: VideoGameUseCase) : ViewModel() {

    private var videoGameList = MutableLiveData<MutableList<VideoGameResponse>>()
    val videoGamesLV
        get()= videoGameList

    fun getAllVideoGamesFromServer(){
        viewModelScope.launch {
            try {
                val response = useCase.getAllVideoGamesInStock()
                if (response.isNotEmpty()) {
                    useCase.saveAllVideoGamesOnStock(response)
                    response.forEach { videoGame ->
                        val detail = useCase.getVideoGameByIdOnStock(videoGame.id)
                        useCase.saveDetailVideoGameOnDB(detail)
                    }
                }
            videoGameList.value = response
            } catch (e: Exception){
                Log.e("Main ViewModel", "Not network connection")
                videoGameList.value = useCase.getAllVideoGamesFromDB()
            }
        }
    }
}