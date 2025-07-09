package com.code4galaxy.workmanagerdemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.code4galaxy.workmanagerdemo.ui.navigation.MainNavigation
import com.code4galaxy.workmanagerdemo.ui.screen.ImageChainScreen
import com.code4galaxy.workmanagerdemo.ui.screen.RetryUploadScreen
import com.code4galaxy.workmanagerdemo.ui.theme.WorkmanagerDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkmanagerDemoTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
//                     QuoteScreen(modifier = Modifier.Companion.padding(innerPadding))
//                    RetryUploadScreen(modifier = Modifier.Companion.padding(innerPadding))
//                    ImageChainScreen(modifier = Modifier.Companion.padding(innerPadding))


                    MainNavigation(Modifier.Companion.padding(innerPadding),navController = navController)
                }
            }
        }
    }
}