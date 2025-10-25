package ipca.example.soluxthecompany.itui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
// Adicionar estes imports para a funcionalidade de scroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ipca.example.soluxthecompany.R

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit
) {
    val login = viewModel.loginState
    val error = viewModel.loginError
    val isLoggedIn = viewModel.isLoggedIn

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            onLoginSuccess()
            viewModel.loginReset()
        }
    }

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF3C3C3C),
            Color(0xFF1C1C1C),
            Color(0xFF000000)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
            .padding(horizontal = 24.dp),
        // Alterado para 'Center' para melhor visualização em ecrãs maiores
        contentAlignment = Alignment.Center
    ) {
        // Criar o estado de scroll
        val scrollState = rememberScrollState()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            // A Column agora pode rolar verticalmente
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            // LOGO
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo SoLuX",
                modifier = Modifier
                    .size(180.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // TÍTULO
            Text(
                text = "Bem-vindo à SoLuX!",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // CAMPO DE EMAIL
            OutlinedTextField(
                value = login.email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = { Text("Email") },
                leadingIcon = {
                    Icon(Icons.Filled.Email, contentDescription = null, tint = Color.LightGray)
                },
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFAAAAAA),
                    unfocusedBorderColor = Color(0xFF555555),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color(0xFFE0E0E0),
                    unfocusedLabelColor = Color(0xFF999999)
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // CAMPO DE PASSWORD
            OutlinedTextField(
                value = login.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = { Text("Password") },
                leadingIcon = {
                    Icon(Icons.Filled.Lock, contentDescription = null, tint = Color.LightGray)
                },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFAAAAAA),
                    unfocusedBorderColor = Color(0xFF555555),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color(0xFFE0E0E0),
                    unfocusedLabelColor = Color(0xFF999999)
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            // BOTÃO DE LOGIN
            Button(
                onClick = { viewModel.onLoginClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF777777),
                                    Color(0xFF3A3A3A),
                                    Color(0xFF1F1F1F)
                                )
                            ),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Entrar",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // MENSAGEM DE ERRO
            error?.let {
                Text(
                    text = it,
                    color = Color(0xFFFF6B6B),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.height(24.dp) // Garante espaço para o erro
                )
            }
        }
    }
}
