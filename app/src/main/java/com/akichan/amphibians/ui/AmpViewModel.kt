package com.akichan.amphibians.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akichan.amphibians.model.AmpItems
import com.akichan.amphibians.network.AmpApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmpUiState {
    data class Success (val ampItems: List<AmpItems>) : AmpUiState
    object Error : AmpUiState
    object Loading : AmpUiState
}

class AmpViewModel: ViewModel() {
    var ampUiState : AmpUiState by mutableStateOf(AmpUiState.Loading)
        private set

    init {
        getAmpData()
    }

    fun getAmpData() {
        viewModelScope.launch {
            ampUiState = AmpUiState.Loading
            ampUiState = try {
                val listResult = AmpApi.retrofitService.getAmps()
                AmpUiState.Success(
                    listResult
                )
            } catch (e: IOException) {
                AmpUiState.Error
            } catch (e: retrofit2.HttpException) {
                AmpUiState.Error
            }
        }
    }
}