package my.com.assessments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import my.com.assessments.model.EngineerX

class ScheduleViewModelFactory(private val engineers: List<EngineerX?>?) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ScheduleViewModel(engineers) as T
    }
}