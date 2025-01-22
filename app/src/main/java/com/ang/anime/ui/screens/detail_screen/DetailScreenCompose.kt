package com.ang.anime.ui.screens.detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ang.anime.data.model.DashBoardResponse
import com.ang.anime.data.model.DataDashBoard
import com.ang.anime.data.remote.AnimeApiService
import com.ang.anime.ui.navigation.DashBoardDetailRoute
import com.ang.anime.ui.screens.dashboard.AnimeItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun DashBoardDetailCompose(navController: NavHostController, service: AnimeApiService,
                           animeId:Int=0
                           ) {
    var resp = remember{ mutableStateOf<DataDashBoard?>(null) }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            resp.value =  service.getAnimeDetails(animeId).body()?.data
        }
    }
    Scaffold(topBar = {
        Box(Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.secondaryContainer).statusBarsPadding()){
            Text(modifier = Modifier.padding(8.dp).align(Alignment.Center), text = "My Anime App",
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.titleLarge,)
        }
    },
        containerColor = MaterialTheme.colorScheme.surfaceDim) {
        Box(Modifier.padding(it)){
            resp.value?.let{
                AnimeItem(it.title?:"",
                    it.episodes?:0,
                    it.score?:0.0,
                    it.images?.jpg?.largeImageUrl?:""){
                    navController.navigate(DashBoardDetailRoute(animeId = it.malId?:0))
                }

            }
        }
    }
}