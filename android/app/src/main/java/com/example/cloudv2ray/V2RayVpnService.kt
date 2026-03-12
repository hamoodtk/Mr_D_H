package com.example.cloudv2ray

import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor
import android.util.Log
import kotlinx.coroutines.*

class V2RayVpnService : VpnService() {
    private var vpnInterface: ParcelFileDescriptor? = null
    private val serviceScope = CoroutineScope(Dispatchers.IO + Job())

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        if (action == "STOP") {
            stopVpn()
            return START_NOT_STICKY
        }

        val config = intent?.getStringExtra("CONFIG") ?: ""
        startVpn(config)
        return START_STICKY
    }

    private fun startVpn(config: String) {
        serviceScope.launch {
            try {
                // In a real app, you would initialize the V2Ray core here
                // libv2ray.V2RayPoint.startLoop()
                
                vpnInterface = Builder()
                    .setSession("Cloud V2Ray")
                    .addAddress("10.0.0.1", 24)
                    .addDnsServer("8.8.8.8")
                    .addRoute("0.0.0.0", 0)
                    .establish()

                Log.d("V2RayVpnService", "VPN Started with config: $config")
                
                // Keep the service alive and handle traffic
                // This is where the V2Ray engine would process packets
            } catch (e: Exception) {
                Log.e("V2RayVpnService", "Error starting VPN", e)
                stopSelf()
            }
        }
    }

    private fun stopVpn() {
        vpnInterface?.close()
        vpnInterface = null
        serviceScope.cancel()
        stopSelf()
        Log.d("V2RayVpnService", "VPN Stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        stopVpn()
    }
}
