package ipca.example.soluxthecompany.itui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeViewModel : ViewModel() {

    private var _currentDate by mutableStateOf(Calendar.getInstance())
    val currentDate: Calendar
        get() = _currentDate

    fun getMonthYearLabel(): String {
        val sdf = SimpleDateFormat("MMMM yyyy", Locale("pt", "PT"))
        return sdf.format(_currentDate.time).replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale("pt","PT")) else it.toString()
        }
    }
    fun getMonthDays(): List<String> {
        val cal = _currentDate.clone() as Calendar
        cal.set(Calendar.DAY_OF_MONTH, 1)

        val firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK) // 1..7

        val leadingEmpty = (firstDayOfWeek - Calendar.SUNDAY) // 0..6

        val daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)

        val cells = mutableListOf<String>()

        repeat(leadingEmpty) { cells.add("") }

        for (d in 1..daysInMonth) {
            cells.add(d.toString())
        }

        while (cells.size % 7 != 0) {
            cells.add("")
        }

        return cells
    }

    fun goToNextMonth() {
        val cal = _currentDate.clone() as Calendar
        cal.add(Calendar.MONTH, 1)
        _currentDate = cal
    }

    fun goToPreviousMonth() {
        val cal = _currentDate.clone() as Calendar
        cal.add(Calendar.MONTH, -1)
        _currentDate = cal
    }
}
