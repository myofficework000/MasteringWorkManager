package com.code4galaxy.workmanagerdemo.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.code4galaxy.workmanagerdemo.viewmodel.ChainDemoViewModel

@Composable
fun ImageChainScreen(modifier: Modifier = Modifier,navController:NavHostController) {
    val viewModel: ChainDemoViewModel = hiltViewModel()
    val state = viewModel.chainState.collectAsStateWithLifecycle().value

    Column(modifier = modifier.padding(16.dp)) {
        Text("Image Processing Demo", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Step: ${state.step}")
        Text("State: ${state.state}")
        Text("Message: ${state.message}")

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.startImageProcessing()
            navController.navigate("RetryUploadScreen")
        }) {
            Text("Start Image Processing")
        }
    }
}