package my.com.assessments.utilities

import java.util.*

fun Date.getWorkingDays(interval: Int): List<Date> {
    val dates = mutableListOf<Date>()
    val calendar: Calendar = Calendar.getInstance()
    calendar.time = this
    for (i in 0 until interval) {
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        dates.add(calendar.time)
        // TODO: Not sure to ignore Saturday Sunday or not
    }

    return dates
}