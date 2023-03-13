package com.example.frog.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.frog.R
import com.example.frog.data.FrogInfoItem

@Composable
fun HomeScreen(frogUiState: screenNetworkFrog, modifier: Modifier = Modifier){
    when(frogUiState){
        is screenNetworkFrog.Success -> SuccessScreen(frogUiState.frogs,modifier)
        is screenNetworkFrog.Loading -> LoadScreen(modifier)
        else -> ErrorScreen(modifier)
    }
}

@Composable
fun LoadScreen(modifier: Modifier) {
    var rotVal by remember { mutableStateOf(720f) }
    var boole by remember { mutableStateOf(true) }
    val angle by animateFloatAsState(
        targetValue = if (boole) 0f else rotVal,
        animationSpec = tween(durationMillis = 1000),

        )
    Box(
        modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.Center) {
        boole = false
        Image(painter = painterResource(id = R.drawable.loading_img), contentDescription = "", modifier = modifier
            .rotate(angle)
            .size(200.dp))

    }
}

@Composable
fun ErrorScreen(modifier: Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.Center) {
        Text(text = "Error")
    }
}

@Composable
fun SuccessScreen(frogs: MutableList<FrogInfoItem>, modifier: Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.Center) {
        LazyVerticalGrid(columns = GridCells.Adaptive(200.dp), contentPadding = PaddingValues(8.dp)){
            items(items =frogs, key = {it.name}){
                photo -> frogCard(frogs = photo)
            }
        }
    }
}


@Composable
fun frogCard(modifier: Modifier = Modifier, frogs: FrogInfoItem){
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.padding(4.dp).fillMaxWidth(),
        contentColor = Color.Black,
        backgroundColor = Color.White
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.padding(16.dp)) {
            Text(text = frogs.name, fontWeight = FontWeight.SemiBold)
            Text(text = frogs.description, textAlign = TextAlign.Justify)
            AsyncImage(model = ImageRequest.Builder(context = LocalContext.current).data(frogs.img_src).crossfade(true).build(), contentDescription = frogs.description, contentScale = ContentScale.Crop, modifier = modifier.fillMaxSize())

        }

    }
}