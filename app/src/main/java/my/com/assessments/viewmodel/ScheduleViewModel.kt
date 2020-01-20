package my.com.assessments.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import my.com.assessments.model.EngineerX
import my.com.assessments.model.Schedule
import my.com.assessments.utilities.NUMBER_OF_SHIFT
import my.com.assessments.utilities.getWorkingDays
import java.util.*
import kotlin.collections.ArrayList

class ScheduleViewModel internal constructor(private val engineers: List<EngineerX>?) :
    ViewModel() {

    // Making sure that our subsequent list that is from existing should not take the last value.
    fun getSchedule(startDate: Date?, lastEngineers: List<EngineerX>?): List<Schedule>? {

        // Ignore if engineers is empty or null to reduce memory usage.
        // Why bother to do calculation if it's empty or null anyway.
        if (engineers?.isNullOrEmpty()!!) {
            return null
        }

        // If startDate is null, generate one from today.
        val start = startDate ?: Date()

        // We have two schedules with 10 engineers. That's basically means that the number of days should be
        // half of our engineer size.
        val totalDays = engineers.size / NUMBER_OF_SHIFT
        val dates = start.getWorkingDays(totalDays)

        val schedules = mutableListOf<Schedule>()

        dates.map {
            val schedule = Schedule()
            schedule.date = it
            val invalidEngineers = mutableListOf<EngineerX>()

            if (lastEngineers?.isNullOrEmpty() == true) {
                invalidEngineers.addAll(lastEngineers)
            }

            removeScheduledEngineer(schedules, invalidEngineers)

            val validEngineers = engineers.filter { eng -> !invalidEngineers.contains(eng) }

            val selectedEngieers = getRandomEngineer(validEngineers, NUMBER_OF_SHIFT)

            schedule.engineers.addAll(selectedEngieers)

            schedules.add(schedule)

        }

        return schedules

    }

    @VisibleForTesting
    fun removeScheduledEngineer(
        schedules: MutableList<Schedule>,
        invalidEngineers: MutableList<EngineerX>
    ) = schedules.forEach { sch -> invalidEngineers.addAll(sch.engineers) }

    // TODO: Do this better.
    @VisibleForTesting
    fun getRandomEngineer(engineers: List<EngineerX>, engineerCount: Int): List<EngineerX> {
        if (engineers.isEmpty()) {
            return emptyList()
        }
        val output = mutableListOf<EngineerX>()
        for (i in 0 until engineerCount) {
            val availableEngineers = ArrayList(engineers)
            if (output.isNotEmpty()) {
                availableEngineers.removeAll(output)
            }
            try {
                output.add(availableEngineers[Random().nextInt(availableEngineers.size - 1)])
            } catch (_: Exception) {
                output.add(availableEngineers[0])
            }
        }
        return output
    }

}
