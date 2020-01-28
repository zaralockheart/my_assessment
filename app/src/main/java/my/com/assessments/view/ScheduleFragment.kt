package my.com.assessments.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import my.com.assessments.R
import my.com.assessments.model.Engineer
import my.com.assessments.utilities.Injectors
import my.com.assessments.viewmodel.ScheduleViewModel

class ScheduleFragment : Fragment() {

    companion object {
        const val ENGINEER = "engineer"
    }

    private val viewModel: ScheduleViewModel by viewModels {
        val bundle = arguments?.getParcelable<Engineer>(ENGINEER)
        Injectors.provideScheduleViewModelFactory(bundle?.engineers)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val firstWeekList = viewModel.getSchedule(null, null)
        val secondWeekList =
            viewModel.getSchedule(firstWeekList!!.last().date, firstWeekList.last().engineers)
        val twoSchedulesMerged = firstWeekList.union(secondWeekList!!.toMutableList())
        twoSchedulesMerged.map {
            Log.d("TAG", it.date.toString())
            it.engineers.map {eng ->
                Log.d("ENGINEERS", eng.name!!)
            }
        }

        return inflater.inflate(R.layout.schedule_fragment, container, false)
    }
}
