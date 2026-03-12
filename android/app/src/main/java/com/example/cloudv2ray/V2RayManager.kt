package com.example.cloudv2ray

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object V2RayManager {
    var isConnected by mutableStateOf(false)
        private set

    fun toggleConnection(context: Context, config: String) {
        if (isConnected) {
            stopVpn(context)
        } else {
            startVpn(context, config)
        }
    }

    private fun startVpn(context: Context, config: String) {
        val intent = Intent(context, V2RayVpnService::class.java).apply {
            putExtra("CONFIG", config)
        }
        context.startService(intent)
        isConnected = true
    }

    private fun stopVpn(context: Context) {
        val intent = Intent(context, V2RayVpnService::class.java).apply {
            action = "STOP"
        }
        context.startService(intent)
        isConnected = false
    }
}
