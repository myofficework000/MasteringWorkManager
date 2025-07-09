package com.code4galaxy.workmanagerdemo.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.code4galaxy.workmanagerdemo.workers.MessageWorkerHelper

@Composable
fun MessageSyncScreen(context: Context) {
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Enter message") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (message.isNotBlank()) {
                    MessageWorkerHelper.saveToLocal(context, message)
                    MessageWorkerHelper.scheduleSyncWorker(context)
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save & Sync")
        }
    }
}
