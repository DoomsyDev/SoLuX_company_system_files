package ipca.example.soluxthecompany.itui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ipca.example.soluxthecompany.itui.components.CalendarView
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onMenuClick: () -> Unit = {}
) {
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF3C3C3C),
            Color(0xFF1C1C1C),
            Color(0xFF000000)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "SoLuX The Startup",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onMenuClick() }) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        bottomBar = { BottomNavigationBar() },
        containerColor = Color.Transparent
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBackground)
                .padding(paddingValues)
                .padding(top = 16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            CalendarView(
                monthYearLabel = viewModel.getMonthYearLabel(),
                days = viewModel.getMonthDays(),
                today = viewModel.currentDate.get(Calendar.DAY_OF_MONTH).toString()
            )
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(containerColor = Color(0xFF1C1C1C)) {
        NavigationBarItem(
            selected = true,
            onClick = { /* TODO */ },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.White) },
            label = { Text("Home", color = Color.White) }
        )

        NavigationBarItem(
            selected = false,
            onClick = { /* TODO */ },
            icon = { Icon(Icons.Filled.DateRange, contentDescription = "Agenda", tint = Color.White) },
            label = { Text("Agenda", color = Color.White) }
        )

        NavigationBarItem(
            selected = false,
            onClick = { /* TODO */ },
            icon = { Icon(Icons.Default.Settings, contentDescription = "Configurações", tint = Color.White) },
            label = { Text("Definições", color = Color.White) }
        )
    }
}
