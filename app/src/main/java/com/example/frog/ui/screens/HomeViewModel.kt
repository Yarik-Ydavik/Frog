package com.example.frog.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frog.data.FrogInfoItem
import com.example.frog.data.FrogRepositories
import com.example.frog.data.defaultFrogRepositories
import com.example.frog.network.FrogApi
import com.example.frog.network.frogApiService
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface screenNetworkFrog{
    data class Success(val frogs : MutableList<FrogInfoItem>):screenNetworkFrog
    object Loading: screenNetworkFrog
    object Error: screenNetworkFrog
}

class HomeViewModel:ViewModel() {
    var frogUiState:screenNetworkFrog by mutableStateOf(screenNetworkFrog.Loading)
        private set

    init {
        getFrogInfo()
    }
    private fun getFrogInfo(){
        viewModelScope.launch {
            frogUiState = try {
                var repos = defaultFrogRepositories()
                var result = repos.getFrog()
                screenNetworkFrog.Success(result)
            }catch (e:IOException){
                screenNetworkFrog.Error
            }
        }
    }
}