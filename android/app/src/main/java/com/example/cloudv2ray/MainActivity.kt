package com.example.cloudv2ray

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VpnTheme {
                VpnApp()
            }
        }
    }
}

@Composable
fun VpnApp() {
    var isConnected by remember { mutableStateOf(false) }
    var timeSeconds by remember { mutableStateOf(0) }
    
    LaunchedEffect(isConnected) {
        if (isConnected) {
            while (true) {
                delay(1000)
                timeSeconds++
            }
        } else {
            timeSeconds = 0
        }
    }

    Scaffold(
        topBar = { VpnHeader() },
        bottomBar = { VpnFooter(isConnected) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GraphArea()
            TimerSection(timeSeconds)
            SpeedMetrics()
            ServerCards()
            PowerButton(isConnected) { isConnected = !isConnected }
            ExpirationDetails()
        }
    }
}

@Composable
fun VpnHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.GridView, contentDescription = null, tint = Color(0xFF475569), modifier = Modifier.size(32.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Cloud V2Ray", fontSize = 28.sp, fontWeight = FontWeight.Black, color = Color(0xFF3B82F6))
                Text("-ViP", fontSize = 28.sp, fontWeight = FontWeight.Black, color = Color(0xFF1E293B))
            }
            Text("lightening and universe xo", fontSize = 12.sp, color = Color(0xFF94A3B8), fontWeight = FontWeight.Bold)
        }
        Icon(Icons.Default.MoreVert, contentDescription = null, tint = Color(0xFF475569), modifier = Modifier.size(32.dp))
    }
}

@Composable
fun GraphArea() {
    Box(modifier = Modifier.fillMaxWidth().height(96.dp).padding(horizontal = 16.dp)) {
        Column(modifier = Modifier.align(Alignment.TopEnd), horizontalAlignment = Alignment.End) {
            Text("0 bit", fontSize = 10.sp, color = Color(0xFFCBD5E1), fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace)
            Text("0 bit", fontSize = 10.sp, color = Color(0xFFCBD5E1), fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace)
            Text("0.0 bit", fontSize = 10.sp, color = Color(0xFFCBD5E1), fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("LiveData", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF94A3B8))
                Spacer(Modifier.width(4.dp))
                Box(Modifier.size(8.dp).background(Color(0xFFF87171), CircleShape))
                Spacer(Modifier.width(4.dp))
                Text("0.0 bit", fontSize = 10.sp, color = Color(0xFFCBD5E1), fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace)
            }
        }
        Divider(color = Color(0xFFF1F5F9), modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 24.dp))
        Row(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(horizontal = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Config: 6.97", fontSize = 11.sp, color = Color(0xFF94A3B8), fontWeight = FontWeight.Bold)
            Text("192.168.0.106", fontSize = 11.sp, color = Color(0xFF94A3B8), fontWeight = FontWeight.Bold)
            Text("Wifi connection", fontSize = 11.sp, color = Color(0xFF94A3B8), fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun TimerSection(seconds: Int) {
    val h = seconds / 3600
    val m = (seconds % 3600) / 60
    val s = seconds % 60
    val timeStr = "%02d:%02d:%02d".format(h, m, s)
    
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(vertical = 16.dp)) {
        Text("Connection Time", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF94A3B8))
        Text(timeStr, fontSize = 64.sp, fontWeight = FontWeight.Medium, color = Color(0xFF3B82F6), fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace)
    }
}

@Composable
fun SpeedMetrics() {
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.ArrowCircleDown, null, tint = Color(0xFF10B981), modifier = Modifier.size(24.dp))
            Spacer(Modifier.width(8.dp))
            Column {
                Text("Download", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF94A3B8))
                Text("0 B", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
            }
        }
        Text("V2Ray/Xray", fontSize = 13.sp, color = Color(0xFF94A3B8), fontWeight = FontWeight.Bold)
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
            Column(horizontalAlignment = Alignment.End) {
                Text("Upload", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF94A3B8))
                Text("0 B", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
            }
            Spacer(Modifier.width(8.dp))
            Icon(Icons.Default.ArrowCircleUp, null, tint = Color(0xFFEF4444), modifier = Modifier.size(24.dp))
        }
    }
}

@Composable
fun ServerCards() {
    Column(Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
        ServerCard("Dynamic Server 6", "Cloud V2Ray ViP", color = Color(0xFF3B82F6), isServer = true)
        Spacer(Modifier.height(12.dp))
        ServerCard("Lebara Free V2ray✅", "(High Speed💥 All Area)", color = Color(0xFF3B82F6), isLebara = true)
    }
}

@Composable
fun ServerCard(title: String, subtitle: String, color: Color, isServer: Boolean = false, isLebara: Boolean = false) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFDBEAFE)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(48.dp).background(Color.White, CircleShape).border(1.dp, Color(0xFFDBEAFE), CircleShape), contentAlignment = Alignment.Center) {
                if (isServer) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                            Box(Modifier.size(4.dp, 8.dp).background(Color(0xFF60A5FA), RoundedCornerShape(2.dp)))
                            Box(Modifier.size(4.dp, 12.dp).background(Color(0xFF60A5FA), RoundedCornerShape(2.dp)))
                            Box(Modifier.size(4.dp, 16.dp).background(Color(0xFF60A5FA), RoundedCornerShape(2.dp)))
                        }
                        Text("5G", fontSize = 10.sp, fontWeight = FontWeight.Black, color = Color(0xFF1E293B))
                    }
                } else if (isLebara) {
                    Box(Modifier.fillMaxSize().background(Color(0xFF60A5FA), CircleShape), contentAlignment = Alignment.Center) {
                        Text("Lebara", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Black)
                    }
                }
            }
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 17.sp, color = Color(0xFF334155))
                Text(subtitle, color = color, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
            if (isServer) {
                Text("~ ms", color = Color(0xFF94A3B8), fontSize = 15.sp, fontWeight = FontWeight.Bold)
            } else {
                Box(Modifier.background(Color(0xFF3B82F6), RoundedCornerShape(100.dp)).padding(horizontal = 8.dp, vertical = 4.dp)) {
                    Text("V2ray/Xray", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun PowerButton(isConnected: Boolean, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(vertical = 32.dp)) {
        Box(
            Modifier
                .size(192.dp)
                .background(if (isConnected) Color(0xFF3B82F6) else Color.White, CircleShape)
                .border(12.dp, if (isConnected) Color(0xFF3B82F6) else Color(0xFFEFF6FF), CircleShape)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.PowerSettingsNew, null, tint = if (isConnected) Color.White else Color(0xFF3B82F6), modifier = Modifier.size(80.dp))
        }
    }
}

@Composable
fun ExpirationDetails() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(bottom = 64.dp)) {
        Text("Account Expiration Details", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF64748B))
        Text("Expiry: March 14, 2026 | 16 Days", color = Color(0xFF3B82F6), fontWeight = FontWeight.Bold, fontSize = 15.sp)
    }
}

@Composable
fun VpnFooter(isConnected: Boolean) {
    BottomAppBar(containerColor = Color(0xFFF8FAFC), modifier = Modifier.height(80.dp)) {
        Row(Modifier.fillMaxWidth().padding(horizontal = 20.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.KeyboardArrowDown, null, tint = Color(0xFF64748B), modifier = Modifier.size(32.dp))
                Spacer(Modifier.width(24.dp))
                Text("STATUS: ${if (isConnected) "CONNECTED" else "DISCONNECTED"}", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF475569))
            }
            Icon(Icons.Default.MoreVert, null, tint = Color(0xFF64748B), modifier = Modifier.size(32.dp))
        }
    }
}

@Composable
fun VpnTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}
