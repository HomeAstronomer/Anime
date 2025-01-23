package com.ang.anime.ui.screens.dashboard

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ang.anime.data.model.DataDashBoard
import com.ang.anime.repository.AnimeRepository
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
data class DashboardUiState(
    val animeList:List<DataDashBoard> =emptyList<DataDashBoard>()

)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DashboardUiState()
    )

    init {
        getDashBoardData()
    }

    private fun getDashBoardData() {
        viewModelScope.launch(Dispatchers.IO){
           val response= animeRepository.getTopData(1)
            _uiState.update {  it.copy(animeList = response.body()?.data?.toList()?:emptyList())}
        }
    }
}