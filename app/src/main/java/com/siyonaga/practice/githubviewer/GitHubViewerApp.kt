package com.siyonaga.practice.githubviewer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun GitHubViewerApp(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "home"
    val firstPartOfCurrentRoute = currentRoute.split("/").first()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (firstPartOfCurrentRoute == "home") {
                // todo: ホーム画面のトップアップバー
            } else if (firstPartOfCurrentRoute == "detail") {
                // todo: 詳細画面のトップアップバー
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = "home") {
                // todo: ホーム画面のコンテンツ
            }
            composable(
                route = "detail/{repoName}",
                arguments = listOf(
                    navArgument(name = "repoName") {
                        type = NavType.StringType
                        nullable = false
                    },
                ),
            ) { entry ->
                val repoName = entry.arguments?.getString("repoName")

                // todo 詳細画面
            }
        }
    }
}
