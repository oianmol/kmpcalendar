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
      val day = LocalDate(date.year, 1, 1)
      val last = LocalDate(date.year, 12, 31)
      val year = JetYear(day, last)
      year.yearMonths = year.months(firstDayOfWeek)
      return year
    }
  }

  private fun months(firstDayOfWeek: DayOfWeek): List<JetMonth> {
    val months = mutableListOf<JetMonth>()

    var startDateMonth = this.startDate.withDayOfMonth(1)

    var endDateMonth = startDateMonth.startDateNextMonth().minus(DatePeriod(days = 1))

    var currentYear = this.startDate.year
    while (true) {
      months.add(JetMonth.current(startDateMonth, firstDayOfWeek))

      startDateMonth = endDateMonth.plus(DatePeriod(days = 1))
      endDateMonth = startDateMonth.startDateNextMonth().minus(DatePeriod(days = 1))
      if (endDateMonth.year > currentYear) {
        break
      }
      currentYear = endDateMonth.year
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
