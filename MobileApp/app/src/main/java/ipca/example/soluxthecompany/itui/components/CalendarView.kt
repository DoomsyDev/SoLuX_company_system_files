package ipca.example.soluxthecompany.itui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalendarView(
    monthYearLabel: String,
    days: List<String>,
    today: String
) {
    val weekDays = listOf("Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = monthYearLabel,
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            weekDays.forEach { day ->
                Text(
                    text = day,
                    color = Color.LightGray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(Modifier.height(8.dp))
        Divider(color = Color.Gray, thickness = 0.5.dp)
        Spacer(Modifier.height(8.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(7)) {
            items(days) { day ->
                DayCell(day = day, isToday = day == today)
            }
        }
    }
}

@Composable
fun DayCell(day: String, isToday: Boolean) {
    val backgroundColor =
        if (isToday) Color(0x7C0E0D0D) else Color.Transparent
    val textColor =
        if (day.isEmpty()) Color.Transparent else if (isToday) Color.White else Color.LightGray

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f)
            .background(color = backgroundColor, shape = CircleShape)
    ) {
        Text(
            text = day,
            color = textColor,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}
