package com.ang.anime.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ang.anime.data.model.DashBoardResponse
import com.ang.anime.data.model.DataDashBoard
import com.ang.anime.data.remote.AnimeApiService
import com.ang.anime.ui.ImageCompose
import com.ang.anime.ui.navigation.DashBoardDetailRoute
import com.ang.anime.ui.theme.AppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun DashBoardCompose(navController: NavHostController, service: AnimeApiService) {


    var resp = remember{ mutableStateOf<DashBoardResponse?>(null) }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
             resp.value =  service.getTopAnime(1).body()
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
        Box(Modifier.fillMaxSize().padding(it)){
            if(resp.value!=null) {
                LazyColumn {
                    items(resp.value?.data?.toList() ?: emptyList<DataDashBoard>()) {
                        AnimeItem(
                            it.title ?: "",
                            it.episodes ?: 0,
                            it.score ?: 0.0,
                            it.images?.jpg?.largeImageUrl ?: ""
                        ) {
                            navController.navigate(DashBoardDetailRoute(animeId = it.malId ?: 0))
                        }

                    }
                }
            }else{
                CircularProgressIndicator(Modifier.size(64.dp).align(Alignment.Center))
            }
        }
    }

}

@Composable
fun AnimeItem(
    title: String,
    episodes: Int,
    rating: Double,
    posterUrl: String,
    onClick:()->Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(16.dp))
            .clickable{
                onClick.invoke()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageCompose(Modifier
            .padding(8.dp)
            .width(80.dp)
            .aspectRatio(0.71186f)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp)),
            data =posterUrl
        )

        Column(modifier = Modifier.weight(1f).padding(4.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Episodes: $episodes",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Rating: $rating",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview
@Composable
fun AnimeItemPreview(){
    AppTheme {
    AnimeItem("ABC",10,9.8,""){}
    }
}