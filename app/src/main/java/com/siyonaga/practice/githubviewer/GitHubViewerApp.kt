package com.siyonaga.practice.githubviewer

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.siyonaga.practice.githubviewer.home.HomeScreen

@Composable
fun GitHubViewerApp(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable(route = "home") {
            // todo: ホーム画面のコンテンツ
            HomeScreen(viewModel = hiltViewModel())
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
