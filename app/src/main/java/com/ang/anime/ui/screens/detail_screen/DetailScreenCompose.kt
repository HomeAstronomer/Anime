package com.ang.anime.ui.screens.detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.ang.anime.data.model.DataDashBoard
import com.ang.anime.data.model.Genres
import com.ang.anime.data.remote.AnimeApiService
import com.ang.anime.ui.ImageCompose
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun DashBoardDetailCompose(
    navController: NavHostController,
    viewModel:DetailScreenViewModel= hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        Box(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .statusBarsPadding()
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Center),
                text = "My Anime App",
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }, containerColor = MaterialTheme.colorScheme.surface) {
        Box(Modifier.fillMaxSize().padding(it)) {
            val scrollState= rememberScrollState()

        if (uiState.value.animeList != null) {

                Column(Modifier.padding(horizontal = 24.dp).verticalScroll(state = scrollState,enabled = true)) {
                    Text(
                        uiState.value.animeList?.title ?: "",
                        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center

                    )
                    if (uiState.value.animeList?.trailer?.youtubeId?.isEmpty() == false) {
                        uiState.value.animeList?.trailer?.youtubeId?.let { id ->
                            YoutubePlayer(id)
                        }
                    } else {
                        ImageCompose(
                            Modifier
                                .fillMaxWidth()
                                .aspectRatio(0.71186f)
                                .background(
                                    MaterialTheme.colorScheme.primaryContainer,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clip(RoundedCornerShape(12.dp)),
                            data = uiState.value.animeList?.images?.jpg?.largeImageUrl ?: ""
                        )
                    }
                    Text(
                        "Plot :- ",
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        uiState.value.animeList?.synopsis ?: "",
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        formatGenres(uiState.value.animeList?.genres.orEmpty()),
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        "Number Of Episodes ;- ${uiState.value.animeList?.episodes ?: 0}",
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Rating: ${uiState.value.animeList?.rating ?: 0}",
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                    )



            }
        } else {
            CircularProgressIndicator(Modifier.size(64.dp).align(Alignment.Center))
        }
    }
    }
}

@Composable
fun YoutubePlayer(youtubeId: String) {
    val context = LocalContext.current
    val player = remember {
        YouTubePlayerView(context).apply {
            getYouTubePlayerWhenReady((object : YouTubePlayerCallback {
                override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(youtubeId, 0.0f)
                }
            }))
        }
    }

    AndroidView(
        factory = { player }, modifier = Modifier.fillMaxWidth()
    )

    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }
}

fun formatGenres(genres: List<Genres?>): String {
    val stringBuilder = StringBuilder("Genre:- ")
    genres.forEachIndexed { index, genre ->
        genre?.name?.let { name ->
            stringBuilder.append(name)
            if (index != genres.lastIndex) { // Add a comma for all except the last item
                stringBuilder.append(", ")
            }
        }
    }
    return stringBuilder.toString()
}