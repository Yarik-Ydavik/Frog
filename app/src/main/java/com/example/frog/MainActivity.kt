package com.example.frog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.frog.ui.screens.HomeScreen
import com.example.frog.ui.screens.HomeViewModel
import com.example.frog.ui.theme.FrogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrogTheme {
                var frogViewModel : HomeViewModel = viewModel()
                HomeScreen(frogViewModel.frogUiState)
            }
        }
    }
}



