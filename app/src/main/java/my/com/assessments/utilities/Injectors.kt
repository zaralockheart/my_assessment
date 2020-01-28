package my.com.assessments.utilities

import my.com.assessments.model.EngineerX
import my.com.assessments.repository.EngineerService
import my.com.assessments.viewmodel.EngineerListViewModelFactory
import my.com.assessments.viewmodel.ScheduleViewModelFactory

object Injectors {

    private fun engineerRepository(): EngineerService = EngineerService()

    fun provideEngineerListViewModelFactory(
    ): EngineerListViewModelFactory {
        return EngineerListViewModelFactory(engineerRepository())
    }

    fun provideScheduleViewModelFactory(
        engineers: List<EngineerX>?
    ): ScheduleViewModelFactory {
        return ScheduleViewModelFactory(engineers)
    }
}