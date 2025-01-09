package com.siyonaga.practice.githubviewer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GitHubViewerApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {},
    ) { innerPadding ->
        Text(
            text = "Hello!",
            modifier = Modifier.padding(innerPadding),
        )
    }
}
