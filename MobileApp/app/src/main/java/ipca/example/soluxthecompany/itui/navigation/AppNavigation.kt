package ipca.example.soluxthecompany.itui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ipca.example.soluxthecompany.itui.HomeScreen
import ipca.example.soluxthecompany.itui.HomeViewModel
import ipca.example.soluxthecompany.itui.LoginScreen
import ipca.example.soluxthecompany.itui.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val drawerGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF2B2B2B), Color(0xFF1A1A1A))
    )

    val drawerItemColors = NavigationDrawerItemDefaults.colors(
        unselectedContainerColor = Color.Transparent,
        selectedContainerColor = Color(0xFF3A3A3A),
        selectedIconColor = Color.White,
        unselectedIconColor = Color(0xFFB0B0B0),
        selectedTextColor = Color.White,
        unselectedTextColor = Color(0xFFB0B0B0)
    )

    //aqui é a logica para abrir e fechar o menu hamburguer
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF2B2B2B),
                                Color(0xFF1A1A1A)
                            )
                        )
                    ),
                drawerContainerColor = Color.Transparent
            ) {
                Text(
                    "Menu",
                    modifier = Modifier.padding(24.dp),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Divider(color = Color.Gray, thickness = 0.5.dp)

                NavigationDrawerItem(
                    label = { Text(text = "Home") },
                    selected = currentRoute == "home",
                    onClick = {
                        scope.launch { drawerState.close() }
                        if (currentRoute != "home") {
                            navController.navigate("home") { launchSingleTop = true }
                        }
                    },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    colors = drawerItemColors,
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                NavigationDrawerItem(
                    label = { Text(text = "Definições") },
                    selected = currentRoute == "settings",
                    onClick = {
                        scope.launch { drawerState.close() }
                        // Descomente quando o ecrã 'settings' for criado
                        // navController.navigate("settings") { launchSingleTop = true }
                    },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Definições") },
                    colors = drawerItemColors,
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                NavigationDrawerItem(
                    label = { Text(text = "Logout") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("login") { popUpTo(0) }
                    },
                    icon = { Icon(Icons.Default.ExitToApp, contentDescription = "Logout") },
                    colors = drawerItemColors,
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }
    ) {
        //adicionar aqui mais ecrãs
        NavHost(navController = navController, startDestination = "login") {
            composable("login") {
                LoginScreen(
                    viewModel = viewModel<LoginViewModel>(),
                    onLoginSuccess = {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }

            composable("home") {
                HomeScreen(
                    viewModel = viewModel<HomeViewModel>(),
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            }
            // composable("settings") { /* O seu ecrã de definições */ }
        }
    }
}
