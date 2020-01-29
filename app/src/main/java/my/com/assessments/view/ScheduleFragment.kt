package my.com.assessments.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import my.com.assessments.adapter.ScheduleAdapter
import my.com.assessments.databinding.ScheduleFragmentBinding
import my.com.assessments.model.Engineer
import my.com.assessments.model.EngineerX
import my.com.assessments.model.Schedule
import my.com.assessments.utilities.Injectors
import my.com.assessments.viewmodel.ScheduleViewModel
import java.util.*

class ScheduleFragment : Fragment() {

    companion object {
        const val ENGINEER = "engineer"
    }

    private val viewModel: ScheduleViewModel by viewModels {
        val bundle = arguments?.getParcelable<Engineer>(ENGINEER)
        Injectors.provideScheduleViewModelFactory(bundle?.engineers)
    }

    private lateinit var binding: ScheduleFragmentBinding

    private val adapter: ScheduleAdapter by lazy {
        ScheduleAdapter()
    }

    private lateinit var schedules: MutableList<Schedule>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ScheduleFragmentBinding.inflate(inflater, container, false)
        binding.scheduleList.adapter = adapter
        schedules = generateTwoSchedules().toMutableList()
        adapter.submitList(schedules)

        // from https://stackoverflow.com/questions/35010556/how-can-i-identify-that-recyclerviews-last-item-is-visible-on-screen/35010819
        binding.scheduleList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val layoutManager = binding.scheduleList.layoutManager as LinearLayoutManager
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    schedules.addAll(
                        generateTwoSchedules(
                            schedules.last().date,
                            schedules.last().engineers
                        )
                    )
                    val newSchedule = arrayListOf(*schedules.toTypedArray())
                    adapter.submitList(newSchedule)
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        return binding.root
    }

    private fun generateTwoSchedules(
        startDate: Date? = null,
        lastEngineers: List<EngineerX>? = null
    ): List<Schedule> {
        val firstWeekList = viewModel.getSchedule(startDate, lastEngineers)
        val secondWeekList =
            viewModel.getSchedule(firstWeekList.last().date, firstWeekList.last().engineers)
        return firstWeekList.union(secondWeekList.toMutableList()).toList()
    }
}
