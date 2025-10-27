package ipca.example.soluxthecompany.itui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CriarScreen(
    viewModel: CriarViewModel,
    onNavigateBack: () -> Unit
) {
    val state = viewModel.terrenoState

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF3C3C3C),
            Color(0xFF1C1C1C),
            Color(0xFF000000)
        )
    )

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color(0xFFAAAAAA),
        unfocusedBorderColor = Color(0xFF555555),
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        focusedLabelColor = Color(0xFFE0E0E0),
        unfocusedLabelColor = Color(0xFF999999),
        cursorColor = Color.White
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Novo Terreno", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color.Transparent
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBackground)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.nome,
                onValueChange = viewModel::onNomeChange,
                label = { Text("Nome do Terreno") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                OutlinedTextField(
                    value = state.dataImplementacao,
                    onValueChange = viewModel::onDataImplementacaoChange,
                    label = { Text("Data Início") },
                    modifier = Modifier.weight(1f),
                    colors = textFieldColors,
                    singleLine = true
                )
                OutlinedTextField(
                    value = state.dataLevantamento,
                    onValueChange = viewModel::onDataLevantamentoChange,
                    label = { Text("Data Fim") },
                    modifier = Modifier.weight(1f),
                    colors = textFieldColors,
                    singleLine = true
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                OutlinedTextField(
                    value = state.latitude,
                    onValueChange = viewModel::onLatitudeChange,
                    label = { Text("Latitude") },
                    modifier = Modifier.weight(1f),
                    colors = textFieldColors,
                    singleLine = true
                )
                OutlinedTextField(
                    value = state.longitude,
                    onValueChange = viewModel::onLongitudeChange,
                    label = { Text("Longitude") },
                    modifier = Modifier.weight(1f),
                    colors = textFieldColors,
                    singleLine = true
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = state.device,
                onValueChange = viewModel::onDeviceChange,
                label = { Text("Device (ex: Sensor #63)") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = state.sampling,
                onValueChange = viewModel::onSamplingChange,
                label = { Text("Sampling (intervalo em min)") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors,
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.onCriarClick()
                    onNavigateBack() // Volta ao ecrã anterior após clicar
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.5f))
            ) {
                Text(
                    "Criar Terreno",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
