package com.baseio.kmm.android

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.baseio.kmm.calendar.JetYear
import com.baseio.kmm.calendar.dayNames
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate

@Composable
fun YearView() {
  val jetYear = JetYear.current(LocalDate(2022, 1, 1), firstDayOfWeek = DayOfWeek.SUNDAY)
  LazyColumn {
    items(jetYear.yearMonths) { jetMonth ->
      Text(jetMonth.startDate.month.name, style = TextStyle(color = Color.Red))
      Row() {
        dayNames(DayOfWeek.SUNDAY).forEach { name ->
          Box(modifier = Modifier.size(40.dp)) {
            Text(name)
          }
        }
      }

      Column() {
        jetMonth.monthWeeks.forEach{ week ->
          LazyRow() {
            items(week.days) { day ->
              Box(modifier = Modifier.size(40.dp)) {
                Text(
                  text = day.date.dayOfMonth.toString(),
                  style = TextStyle(color = if (day.isPartOfMonth) Color.Black else Color.Gray)
                )
              }
            }
          }
        }
      }
    }

  }
}
