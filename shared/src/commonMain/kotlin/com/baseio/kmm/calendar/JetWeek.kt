package com.baseio.kmm.calendar

import kotlinx.datetime.*

fun dayNames(dayOfWeek: DayOfWeek): List<String> {
  val days = weekDays(dayOfWeek)
  return days.map {
    it.name.substring(0, 1)
  }
}

private fun weekDays(dayOfWeek: DayOfWeek): MutableList<DayOfWeek> {
  val days = mutableListOf<DayOfWeek>()
  days.add(dayOfWeek)
  while (days.size != 7) {
    days.add(days.last().next())
  }
  return days
}

private fun DayOfWeek.next(): DayOfWeek {
  val currentIndex = this.ordinal
  val values = DayOfWeek.values()
  val isLast = values.size.minus(1) == currentIndex
  return if (isLast) {
    values.first()
  } else {
    values[currentIndex + 1]
  }
}

class JetWeek private constructor(
  val startDate: LocalDate,
  val endDate: LocalDate,
  val monthOfWeek: Int,
  val dayOfWeek: DayOfWeek,
) : JetCalendarType() {
  lateinit var days: List<JetDay>

  companion object {
    fun current(
      date: LocalDate,
      dayOfWeek: DayOfWeek
    ): JetWeek {
      val startOfCurrentWeek: LocalDate = date.dateOfCurrentWeek(dayOfWeek, true)
      val endOfWeek: LocalDate = date.dateOfCurrentWeek(dayOfWeek, true)
      val week = JetWeek(startOfCurrentWeek, endOfWeek, date.monthNumber, dayOfWeek)
      week.days = week.dates()
      return week
    }
  }

  fun dates(): List<JetDay> {
    val days = mutableListOf<JetDay>()
    val isPart = startDate.monthNumber == this.monthOfWeek
    days.add(startDate.toJetDay(isPart))
    while (days.size != 7) {
      days.add(days.last().nextDay(this))
    }
    return days
  }

}

fun LocalDate.dateOfCurrentWeek(dayOfWeek: DayOfWeek, isFirst: Boolean): LocalDate {
  return if (this.dayOfWeek == dayOfWeek) {
    this
  } else {
    // we are somewhere in the week
    val calendarWeek = weekDays(dayOfWeek)
    val atIndex = calendarWeek.indexOf(this.dayOfWeek)
    // if we are at
    if (isFirst) {
      this.minus(DatePeriod(days = atIndex))
    } else {
      this.plus(DatePeriod(days = atIndex))
    }
  }
}


fun LocalDate.toJetDay(isPart: Boolean): JetDay {
  return JetDay(this, isPart)
}

private fun JetDay.nextDay(jetWeek: JetWeek): JetDay {
  val date = this.date.plus(DatePeriod(days = 1))
  val isPartOfMonth = this.date.plus(DatePeriod(days = 1)).monthNumber == jetWeek.monthOfWeek
  return JetDay(date, isPartOfMonth)
}

fun JetWeek.nextWeek(): JetWeek {
  val firstDay = this.endDate.plus(DatePeriod(days = 1))
  val week = JetWeek.current(firstDay, dayOfWeek = dayOfWeek)
  week.days = week.dates()
  return week
}

