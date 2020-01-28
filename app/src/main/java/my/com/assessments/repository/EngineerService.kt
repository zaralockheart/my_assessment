package my.com.assessments.repository

import my.com.assessments.model.Engineer
import my.com.assessments.utilities.service

class EngineerService{
    suspend fun getEngineers(): Engineer = service().getEngineers()
}