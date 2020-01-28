package my.com.assessments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import kotlinx.coroutines.runBlocking
import my.com.assessments.model.Engineer
import my.com.assessments.model.EngineerX
import my.com.assessments.repository.EngineerService
import my.com.assessments.viewmodel.EngineerListViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EngineerTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var service: EngineerService

    private val observer: Observer<Engineer> = mock()

    @Test
    fun testFetchEgnineers() {

        MockitoAnnotations.initMocks(this)
        val engineerResponse = Engineer(
            listOf(
                EngineerX(0, "engineer 0"),
                EngineerX(1, "engineer 1"),
                EngineerX(2, "engineer 2"),
                EngineerX(3, "engineer 3")
            )
        )

        service.stub {
            onBlocking { getEngineers() }.doReturn(engineerResponse)
        }
        val engineerViewModel = EngineerListViewModel(service)
        runBlocking { engineerViewModel.getEngineer() }

        engineerViewModel.engineer.observeForever(observer)
        verify(observer).onChanged(engineerResponse)
    }
}