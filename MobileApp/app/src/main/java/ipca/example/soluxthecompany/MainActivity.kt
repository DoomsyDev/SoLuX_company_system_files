package ipca.example.soluxthecompany

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ipca.example.soluxthecompany.itui.navigation.AppNavigation
import ipca.example.soluxthecompany.ui.theme.SoLuXTheCompanyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SoLuXTheCompanyTheme {
                AppNavigation()
            }
        }
    }
}
