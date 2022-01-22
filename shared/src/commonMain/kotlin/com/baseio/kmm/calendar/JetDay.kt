package com.baseio.kmm.calendar

import kotlinx.datetime.LocalDate

data class JetDay(val date: LocalDate, val isPartOfMonth: Boolean) : JetCalendarType()