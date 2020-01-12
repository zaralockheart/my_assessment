package my.com.assessments.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import my.com.assessments.model.Engineer
import my.com.assessments.repository.EngineerRepository

class EngineerListViewModel internal constructor(
    private val engineerRepository: EngineerRepository
) : ViewModel() {
    val engineer: MutableLiveData<Engineer> = MutableLiveData()

    suspend fun getEngineer() {
        // TODO(Yusuf): Change this to data from outside
        if (engineer.value == null) {
            val engineers = engineerRepository.getEngineers()
            GlobalScope.launch(Dispatchers.Main) {
                engineer.value = engineers
            }
        }
    }
}
