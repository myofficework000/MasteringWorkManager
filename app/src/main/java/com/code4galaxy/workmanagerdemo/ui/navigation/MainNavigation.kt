package com.code4galaxy.workmanagerdemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.code4galaxy.workmanagerdemo.ui.screen.ImageChainScreen
import com.code4galaxy.workmanagerdemo.ui.screen.RetryUploadScreen


@Composable
fun MainNavigation(modifier: Modifier = Modifier,navController:NavHostController) {

    NavHost(
        navController,
        startDestination = "RetryUploadScreen"

    ) {

        composable("RetryUploadScreen"){
            println("ishk")
            RetryUploadScreen(navController = navController)
        }
        composable("ImageChainScreen"){
            ImageChainScreen(navController = navController)
        }
//        composable(".."){
//            RetryUploadScreen()
//        }
    }
}