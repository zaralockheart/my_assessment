package my.com.assessments.model

import java.util.*

// Let's use this as schedule for each day.
data class Schedule(
    var id: Int? = null,
    var date: Date? = null,
    var engineers: MutableList<EngineerX> = mutableListOf<EngineerX>()
)