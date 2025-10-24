package ipca.example.soluxthecompany.itui

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import ipca.example.soluxthecompany.data.Login

class LoginViewModel : ViewModel() {

    var loginState by mutableStateOf(Login())
        private set

    var loginError by mutableStateOf<String?>(null)
        private set

    var isLoggedIn by mutableStateOf(false)
        private set

    fun onEmailChange(newEmail: String) {
        loginState = loginState.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        loginState = loginState.copy(password = newPassword)
    }

    fun onLoginClick() {
        if (loginState.email == "admin@solux.com" && loginState.password == "1234") {
            loginError = null
            isLoggedIn = true
        } else {
            loginError = "Credenciais inválidas"
            isLoggedIn = false
        }
    }

    fun loginReset() {
        loginState = Login()
        loginError = null
        isLoggedIn = false
    }
}
