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
            Text("Bem-vindo à Home Screen!")
        }
    }
}
