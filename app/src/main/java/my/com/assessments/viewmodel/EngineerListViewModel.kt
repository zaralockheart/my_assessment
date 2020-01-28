package my.com.assessments.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import my.com.assessments.model.Engineer
import my.com.assessments.repository.EngineerService

class EngineerListViewModel internal constructor(
    private val engineerRepository: EngineerService
) : ViewModel() {
    val engineer: MutableLiveData<Engineer> = MutableLiveData()

    suspend fun getEngineer() {
        engineer.value = engineer.value ?: engineerRepository.getEngineers()
    }
}
