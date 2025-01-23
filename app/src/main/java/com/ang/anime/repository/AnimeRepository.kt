package com.ang.anime.repository

import com.ang.anime.data.model.DashBoardResponse
import com.ang.anime.data.model.DetailScreenResponse
import com.ang.anime.data.remote.AnimeApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepository @Inject constructor(
    private  val networkService:AnimeApiService,

) {
    suspend fun getTopData(page:Int): Response<DashBoardResponse> {
        return networkService.getTopAnime(page)
    }

    suspend fun getSpecificAnime(animeId:Int): Response<DetailScreenResponse> {
        return networkService.getAnimeDetails(animeId)
    }

}