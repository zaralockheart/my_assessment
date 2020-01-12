package my.com.assessments.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import my.com.assessments.model.EngineerX

class ScheduleViewModel internal constructor(engineers: List<EngineerX?>?) :
    ViewModel() {

    val engineer: MutableLiveData<EngineerX> = MutableLiveData()

    init {
        engineer.value = engineers?.get(0)
    }
}
