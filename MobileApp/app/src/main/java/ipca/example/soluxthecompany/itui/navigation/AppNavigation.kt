package ipca.example.soluxthecompany.itui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ipca.example.soluxthecompany.itui.LoginScreen
import ipca.example.soluxthecompany.itui.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Text
import ipca.example.soluxthecompany.itui.HomeScreen
import ipca.example.soluxthecompany.itui.HomeViewModel
@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccess = { navController.navigate("home") }
            )
        }

        composable("home") {
            val homeViewModel: HomeViewModel = viewModel()
            HomeScreen(viewModel = homeViewModel)
        }
    }
}
