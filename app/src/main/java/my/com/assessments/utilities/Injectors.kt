package my.com.assessments.utilities

import my.com.assessments.model.EngineerX
import my.com.assessments.repository.EngineerRepository
import my.com.assessments.viewmodel.EngineerListViewModelFactory
import my.com.assessments.viewmodel.ScheduleViewModelFactory

object Injectors {

    private fun getGardenPlantingRepository(): EngineerRepository = EngineerRepository()

    fun provideEngineerListViewModelFactory(
    ): EngineerListViewModelFactory {
        return EngineerListViewModelFactory(getGardenPlantingRepository())
    }

    fun provideScheduleViewModelFactory(
        engineers: List<EngineerX?>?
    ): ScheduleViewModelFactory {
        return ScheduleViewModelFactory(engineers)
    }
}