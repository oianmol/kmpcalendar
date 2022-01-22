package com.baseio.kmm.calendar

import kotlinx.datetime.*

class JetYear private constructor(
  val startDate: LocalDate,
  val endDate: LocalDate,
) : JetCalendarType() {
  lateinit var yearMonths: List<JetMonth>

  companion object {
    fun current(
      date: LocalDate,
      firstDayOfWeek: DayOfWeek
    ): JetYear {
      val day = LocalDate(date.year, Month.JANUARY, 1)
      val last = LocalDate(date.year, Month.DECEMBER, 31)
      val year = JetYear(day, last)
      year.yearMonths = year.months(firstDayOfWeek)
      return year
    }
  }

  private fun months(firstDayOfWeek: DayOfWeek): List<JetMonth> {
    val months = mutableListOf<JetMonth>()
    months.add(JetMonth.current(this.startDate, firstDayOfWeek))
    while (months.size != 12) {
      val nextMonth = months.last().nextMonth()
      months.add(nextMonth)
    }
    return months
  }

  fun year(): String {
    return this.startDate.year.toString()
  }

  private fun LocalDate.withDayOfMonth(dayOfMonth: Int): LocalDate {
    return if (this.dayOfMonth == dayOfMonth) {
      this
    } else LocalDate(year, month.number, dayOfMonth)
  }
}

private fun LocalDate.startDateNextMonth(): LocalDate {
  return LocalDate(this.year, this.monthNumber + 1, 1)
}
