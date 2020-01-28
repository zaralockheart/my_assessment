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
        val start = getTodayIfStartDateNull(startDate)

        // We have two shifts with X number of engineers. That's basically means that the number of days should be
        // half of our engineer size.
        val totalDays = getDayCount()
        val dates = start.getWorkingDays(totalDays)

        val schedules = mutableListOf<Schedule>()

        // This start at O(X)
        dates.map {
            val schedule = Schedule()
            schedule.date = it
            val invalidEngineers = mutableListOf<EngineerX>()

            // This should be O(ln(X - 2)) at first loop
            removeLastEngineer(schedules, invalidEngineers, lastEngineers)

            // This should be O(ln(X - Y)) as Y is schedules length
            removeScheduledEngineer(schedules, invalidEngineers)

            // This should be O(ln(X - Y) + ln(X - Z)) as Z is invalid engineers
            val validEngineers = engineers.filter { eng -> !invalidEngineers.contains(eng) }

            // This should be O(ln(X - Y + SHIFT)) since we always loop SHIFT times.
            val selectedEngineers = getRandomEngineer(validEngineers, NUMBER_OF_SHIFT)

            schedule.engineers.addAll(selectedEngineers)

            schedules.add(schedule)
        }

        return schedules

    }

    @VisibleForTesting
    fun getDayCount(): Int = engineers!!.size / NUMBER_OF_SHIFT

    @VisibleForTesting
    fun getTodayIfStartDateNull(date: Date?): Date = date ?: Date()


    @VisibleForTesting
    fun removeLastEngineer(
        schedules: MutableList<Schedule>,
        invalidEngineers: MutableList<EngineerX>,
        lastEngineers: List<EngineerX>?
    ) {
        if (schedules.isEmpty() && !lastEngineers.isNullOrEmpty()) {
            invalidEngineers.addAll(lastEngineers)
            assert(invalidEngineers.size == 2)
        }
    }

    @VisibleForTesting
    fun removeScheduledEngineer(
        schedules: MutableList<Schedule>,
        invalidEngineers: MutableList<EngineerX>
    ) = schedules.forEach { sch -> invalidEngineers.addAll(sch.engineers) }


    @VisibleForTesting
    fun getRandomEngineer(engineers: List<EngineerX>, shiftCount: Int): List<EngineerX> {
        if (engineers.isEmpty()) {
            return emptyList()
        }
        val output = mutableListOf<EngineerX>()
        for (i in 0 until shiftCount) {
            val availableEngineers = ArrayList(engineers)
            if (output.isNotEmpty()) {
                availableEngineers.removeAll(output)
            }
            output.add(availableEngineers[Random().nextInt(availableEngineers.size)])
        }
        return output
    }

}
