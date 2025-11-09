package ipca.example.soluxthecompany.itui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ipca.example.soluxthecompany.data.Criar

// Data class para guardar as informações do formulário

class CriarViewModel : ViewModel() {

    var terrenoState by mutableStateOf(Criar.TerrenoState())
        private set

    // Funções para atualizar cada campo individualmente
    fun onNomeChange(newValue: String) {
        terrenoState = terrenoState.copy(nome = newValue)
    }

    fun onDataImplementacaoChange(newValue: String) {
        terrenoState = terrenoState.copy(dataImplementacao = newValue)
    }

    fun onDataLevantamentoChange(newValue: String) {
        terrenoState = terrenoState.copy(dataLevantamento = newValue)
    }

    fun onLatitudeChange(newValue: String) {
        terrenoState = terrenoState.copy(latitude = newValue)
    }

    fun onLongitudeChange(newValue: String) {
        terrenoState = terrenoState.copy(longitude = newValue)
    }

    fun onDeviceChange(newValue: String) {
        terrenoState = terrenoState.copy(device = newValue)
    }

    fun onSamplingChange(newValue: String) {
        terrenoState = terrenoState.copy(sampling = newValue)
    }

    fun onCriarClick() {
        // TODO: Adicionar aqui a lóica para guardar os dados
        // ex: enviar para uma base de dados, API, etc.
        println("A criar terreno: $terrenoState")
    }
}
