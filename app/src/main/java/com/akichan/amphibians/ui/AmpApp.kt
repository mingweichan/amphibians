package com.akichan.amphibians.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.akichan.amphibians.ui.screens.Homescreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmpApp() {
    Scaffold (
        modifier = Modifier,
        topBar = { AmpTopBar() }
    ) {
        Surface (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ){
            val ampViewModel: AmpViewModel = viewModel()
            Homescreen()
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmpTopBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
        Text(
            text = "Amphibians",
            style = MaterialTheme.typography.headlineSmall
        )},
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
                         IconButton(
                             onClick = { /*TODO*/ },
                             colors = IconButtonDefaults.iconButtonColors(
                                 contentColor = MaterialTheme.colorScheme.primary
                             )
                         ) {
                             Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
                         }
        },
        modifier = Modifier
    )


}