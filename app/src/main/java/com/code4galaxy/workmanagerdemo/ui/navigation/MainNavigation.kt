package com.code4galaxy.workmanagerdemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.code4galaxy.workmanagerdemo.ui.screen.ImageChainScreen
import com.code4galaxy.workmanagerdemo.ui.screen.MessageSyncScreen
import com.code4galaxy.workmanagerdemo.ui.screen.RetryUploadScreen

@Composable
fun MainNavigation(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController,
        startDestination = "ImageChainScreen"
    ) {
        composable("RetryUploadScreen") {
            RetryUploadScreen(navController = navController)
        }
        composable("ImageChainScreen") {
            ImageChainScreen(navController = navController)
        }
        composable("sync_message") {
            MessageSyncScreen(context = LocalContext.current)
        }
    }
}