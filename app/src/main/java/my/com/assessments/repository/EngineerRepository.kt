package my.com.assessments.repository

import kotlinx.coroutines.delay
import my.com.assessments.model.Engineer
import my.com.assessments.model.EngineerX

class EngineerRepository {
    suspend fun getEngineers(): Engineer {
        return Engineer(
            arrayListOf(
                EngineerX(0, "Bogdan"),
                EngineerX(1, "Nic"),
                EngineerX(2, "Tung"),
                EngineerX(3, "Gautam"),
                EngineerX(4, "Bala"),
                EngineerX(5, "Nazih"),
                EngineerX(6, "Huteri"),
                EngineerX(7, "Aldy"),
                EngineerX(8, "Ankur"),
                EngineerX(9, "Chinh")
            )
        )
    }
}