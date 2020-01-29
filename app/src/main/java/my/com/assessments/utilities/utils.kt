package my.com.assessments.utilities

import java.util.*

fun Date.getWorkingDays(interval: Int): List<Date> {
    val dates = mutableListOf<Date>()
    for (i in 0 until interval) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = this
        calendar.add(Calendar.DAY_OF_WEEK, i)
        dates.add(calendar.time)
        // TODO: Not sure to ignore Saturday Sunday or not
    }

    return dates
}
