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
import com.code4galaxy.workmanagerdemo.viewmodel.RetryWorkerViewModel

@Composable
fun RetryUploadScreen(modifier: Modifier = Modifier,navController: NavHostController) {
    val retryWorkerViewModel: RetryWorkerViewModel = hiltViewModel()
    val retryState = retryWorkerViewModel.retryState.collectAsStateWithLifecycle()

    Column(modifier = modifier.padding(16.dp)) {
        Text("Upload with Retry", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("State: ${retryState.value.state}")
        Text("Attempts: ${retryState.value.attempts}")
        Text("Message: ${retryState.value.message}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { retryWorkerViewModel.startRetryingUpload()
            navController.navigate("ImageChainScreen")
        }) {
            Text("Start Upload")

        }
    }
}