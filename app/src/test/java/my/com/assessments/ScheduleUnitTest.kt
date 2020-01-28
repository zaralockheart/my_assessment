package my.com.assessments

import my.com.assessments.model.EngineerX
import my.com.assessments.model.Schedule
import my.com.assessments.viewmodel.ScheduleViewModel
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class ScheduleUnitTest {
    private val totalEngineers = listOf(
        EngineerX(0, "engineer 0"),
        EngineerX(1, "engineer 1"),
        EngineerX(2, "engineer 2"),
        EngineerX(3, "engineer 3")
    )

    @Test
    fun testPickedEngineerAlwaysPickTwo() {

        val viewModel = ScheduleViewModel(totalEngineers)

        val pickedEngineers = viewModel.getRandomEngineer(totalEngineers, 2)
        assertEquals(2, pickedEngineers.size)
    }

    @Test
    fun testAddInvalidEngineersFromExistingSchedule() {

        val pickedEngineers = listOf(
            totalEngineers[0],
            totalEngineers[2]
        )

        val currentSchedule = mutableListOf(
            Schedule(0, Calendar.getInstance().time, pickedEngineers.toMutableList())
        )

        val viewModel = ScheduleViewModel(totalEngineers)

        val invalidEngineers = mutableListOf<EngineerX>()

        viewModel.removeScheduledEngineer(currentSchedule, invalidEngineers)
        assertEquals(2, invalidEngineers.size)
        assertEquals(true, invalidEngineers.contains(totalEngineers[0]))
        assertEquals(true, invalidEngineers.contains(totalEngineers[2]))
    }

    @Test
    fun `If schedule is empty invalid engineers should append last engineer`() {

        val lastEngineer = listOf(
            totalEngineers[1],
            totalEngineers[3]
        )

        val currentSchedule = mutableListOf<Schedule>()

        val viewModel = ScheduleViewModel(totalEngineers)

        val invalidEngineers = mutableListOf<EngineerX>()

        viewModel.removeLastEngineer(currentSchedule, invalidEngineers, lastEngineer)
        assertEquals(2, invalidEngineers.size)
        assertEquals(true, invalidEngineers.contains(totalEngineers[1]))
        assertEquals(true, invalidEngineers.contains(totalEngineers[3]))
    }

    @Test
    fun `If schedule is not empty invalid engineers should not append last engineer`() {

        val pickedEngineers = listOf(
            totalEngineers[0],
            totalEngineers[2]
        )

        val lastEngineer = listOf(
            totalEngineers[1],
            totalEngineers[3]
        )

        val currentSchedule = mutableListOf(
            Schedule(0, Calendar.getInstance().time, pickedEngineers.toMutableList())
        )

        val viewModel = ScheduleViewModel(totalEngineers)

        val invalidEngineers = mutableListOf<EngineerX>()

        viewModel.removeLastEngineer(currentSchedule, invalidEngineers, lastEngineer)
        assertEquals(0, invalidEngineers.size)
    }
}
