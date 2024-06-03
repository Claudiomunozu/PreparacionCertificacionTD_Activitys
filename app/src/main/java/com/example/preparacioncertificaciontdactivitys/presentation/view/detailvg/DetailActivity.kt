package com.example.preparacioncertificaciontdactivitys.presentation.view.detailvg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.preparacioncertificaciontdactivitys.data.local.database.AppDataBase
import com.example.preparacioncertificaciontdactivitys.data.network.api.VideoGameService
import com.example.preparacioncertificaciontdactivitys.data.network.retrofit.RetrofitHelper
import com.example.preparacioncertificaciontdactivitys.data.repository.VideoGameImpl
import com.example.preparacioncertificaciontdactivitys.databinding.ActivityDetailBinding
import com.example.preparacioncertificaciontdactivitys.domain.VideoGameUseCase
import com.example.preparacioncertificaciontdactivitys.presentation.viewmodel.detailvg.DetailViewModel
import com.example.preparacioncertificaciontdactivitys.presentation.viewmodel.detailvg.ViewModelDetailFactory
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private lateinit var bindingDetail: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingDetail = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bindingDetail.root)

        val idVideogame = intent.getLongExtra("ID_VIDEO_GAME", -1)
        if (idVideogame == -1L) {
            finish()
        }

        val apiService = RetrofitHelper.getRetrofit().create(VideoGameService::class.java)
        val database = AppDataBase.getDatabase(application)
        val repository = VideoGameImpl(apiService, database.videoGameDao())
        val useCase = VideoGameUseCase(repository)
        //val viewModel = VideoGameViewModel(useCase)
        val viewModelFactory = ViewModelDetailFactory(useCase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]

        viewModel.getDetailVideoGameById(idVideogame)

        viewModel.videoGameDetailLV.observe(this) {

            with(it) {

                bindingDetail.txtNameVideoGame.text = name
                bindingDetail.ratingBar.rating = rating.toFloat()
                bindingDetail.txtReleasedVideoGame.text = released
                bindingDetail.txtGenre.text = genres
                bindingDetail.txtMetacritic.text = metacritic.toString()
                bindingDetail.txtPrice.text = price.toString()
                bindingDetail.txtLastPrice.text = lastPrice.toString()
                Picasso
                    .get()
                    .load(backgroundImage)
                    .into(bindingDetail.imgVideoGame)
            }
        }
    }
}
