package com.akichan.amphibians.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.akichan.amphibians.model.AmpItems
import com.akichan.amphibians.ui.AmpUiState
import com.akichan.amphibians.ui.AmpViewModel
import org.jetbrains.annotations.Async

@Composable
fun Homescreen(ampUiState: AmpUiState, retryAction: () -> Unit) {
    when (ampUiState) {
        is AmpUiState.Loading -> LoadingScreen()
        is AmpUiState.Success -> AmpFeed(ampItems = ampUiState.ampItems)
        is AmpUiState.Error -> ErrorScreen(retryAction)
    }
}

@Composable
fun LoadingScreen() {
    Text(text = "Loading...")
}
@Composable
fun AmpFeed(ampItems: List<AmpItems>) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier,
        contentPadding = PaddingValues(top = 16.dp)
    ){

        items(ampItems) { ampItem ->
            AmpCard(name = ampItem.name, description = ampItem.description, imgSrc = ampItem.imgSrc)
        }
    }
}

@Composable
fun AmpCard(name: String, description: String, imgSrc: String) {
    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
        ){
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(16.dp)
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(imgSrc)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = description,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}
@Composable
fun ErrorScreen(retryAction: () -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "There's an error.")
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = retryAction
        ) {
            Text(text = "Retry")
        }
    }

}