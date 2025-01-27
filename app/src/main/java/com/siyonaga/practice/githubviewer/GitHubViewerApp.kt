package com.siyonaga.practice.githubviewer

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.siyonaga.practice.githubviewer.home.HomeScreen

@Composable
fun GitHubViewerApp(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Home,
    ) {
        composable<Home> {
            // todo: ホーム画面のコンテンツ
            HomeScreen(
                viewModel = hiltViewModel(),
                onNavigateToDetail = { ownerName, repoName ->
                    navController.navigate(
                        Detail(ownerName = ownerName, repoName = repoName),
                    )
                },
            )
        }
        composable<Detail> { backStackEntry ->
            // todo 詳細画面
            val detail: Detail = backStackEntry.toRoute()
        }
    }
}
