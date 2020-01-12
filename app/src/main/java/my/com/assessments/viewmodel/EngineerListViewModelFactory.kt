package my.com.assessments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import my.com.assessments.repository.EngineerRepository

class EngineerListViewModelFactory(private val repository: EngineerRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EngineerListViewModel(repository) as T
    }
}