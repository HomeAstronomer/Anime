package com.ang.anime.data.remote

import com.ang.anime.data.model.DashBoardResponse
import com.ang.anime.data.model.DataDashBoard
import com.ang.anime.data.model.DetailScreenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApiService {

    @GET("v4/top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int
    ): Response<DashBoardResponse>

    @GET("v4/anime/{anime_id}")
    suspend fun getAnimeDetails(
        @Path("anime_id") animeId: Int
    ): Response<DetailScreenResponse>
}
