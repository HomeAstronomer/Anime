package com.ang.anime.ui.screens.detail_screen

import androidx.compose.runtime.Immutable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ang.anime.data.model.DataDashBoard
import com.ang.anime.repository.AnimeRepository
import com.ang.anime.ui.navigation.DashBoardDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@Immutable
data class DetailScreenUiState(
    val animeList:DataDashBoard? =null

)

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
    private val savedStateHandle: SavedStateHandle
    ) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailScreenUiState())
    val uiState: StateFlow<DetailScreenUiState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DetailScreenUiState()
    )

    init {
        val animeId=savedStateHandle.toRoute<DashBoardDetailRoute>().animeId
        getAnimeData(animeId)
    }

    private fun getAnimeData(animeId:Int) {
        viewModelScope.launch(Dispatchers.IO){
            val response= animeRepository.getSpecificAnime(animeId)
            _uiState.update {  it.copy(animeList = response.body()?.data)}
        }
    }
}