package ipca.example.soluxthecompany.itui

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ipca.example.soluxthecompany.data.Login

class LoginViewModel : ViewModel() {

    var loginState by mutableStateOf(Login())
        private set

    var loginError by mutableStateOf<String?>(null)
        private set

    var isLoggedIn by mutableStateOf(false)
        private set

    private val auth: FirebaseAuth = Firebase.auth

    fun onEmailChange(newEmail: String) {
        loginState = loginState.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        loginState = loginState.copy(password = newPassword)
    }

    fun onLoginClick() {
        if (loginState.email.isBlank() || loginState.password.isBlank()) {
            // Login falhou pelos campos estarem vazios
            loginError = "Email e password não podem estar vazios."
            return
        }

        auth.signInWithEmailAndPassword(loginState.email.trim(), loginState.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login bem-sucedido
                    loginError = null
                    isLoggedIn = true
                } else {
                    // Login falhou
                    val exception = task.exception
                    when (exception) {
                        is FirebaseAuthInvalidUserException -> {
                            loginError = "Não existe uma conta com o email indicado."
                        }
                        is FirebaseAuthInvalidCredentialsException -> {
                            loginError = "A password está incorreta."
                        }
                        is FirebaseNetworkException -> {
                            loginError = "Erro de rede."
                        }
                        else -> {
                            loginError = "Email ou password inválidos."
                            println("Firebase Auh Error: ${exception?.localizedMessage}")
                        }
                    }
                    isLoggedIn = false
                }
            }
    }

    fun loginReset() {
        loginState = Login()
        loginError = null
        isLoggedIn = false
    }
}
