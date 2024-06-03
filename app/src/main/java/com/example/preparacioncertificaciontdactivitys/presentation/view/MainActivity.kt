package com.example.preparacioncertificaciontdactivitys.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.preparacioncertificaciontdactivitys.data.local.database.AppDataBase
import com.example.preparacioncertificaciontdactivitys.data.network.api.VideoGameService
import com.example.preparacioncertificaciontdactivitys.data.network.retrofit.RetrofitHelper
import com.example.preparacioncertificaciontdactivitys.data.repository.VideoGameImpl
import com.example.preparacioncertificaciontdactivitys.databinding.ActivityMainBinding
import com.example.preparacioncertificaciontdactivitys.domain.VideoGameUseCase
import com.example.preparacioncertificaciontdactivitys.presentation.view.detailvg.DetailActivity
import com.example.preparacioncertificaciontdactivitys.presentation.viewmodel.listvg.VideoGameViewModel
import com.example.preparacioncertificaciontdactivitys.presentation.viewmodel.listvg.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = RetrofitHelper.getRetrofit().create(VideoGameService::class.java)
        val database = AppDataBase.getDatabase(application)
        val repository = VideoGameImpl(apiService, database.videoGameDao())
        val useCase = VideoGameUseCase(repository)
        //val viewModel = VideoGameViewModel(useCase)
        val viewModelFactory = ViewModelFactory(useCase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[VideoGameViewModel::class.java]

        viewModel.getAllVideoGamesFromServer()

        val adapterVideoGame = VideoGameAdapter()
        binding.vgRecyclerView.adapter = adapterVideoGame
        binding.vgRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.videoGamesLV.observe(this) {
            Log.d("GAMES", it.toString())
            adapterVideoGame.videoGames = it
        }

        adapterVideoGame.onItemClickListener = { videoGame ->
            val idVideoGame = videoGame.id
            val nombreVideoGame = videoGame.name
            goToVideoGameDetailPage(idVideoGame)
        }
    }

    private fun goToVideoGameDetailPage(idVideoGame: Long) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("ID_VIDEO_GAME", idVideoGame)
        }
        startActivity(intent)
    }
}




