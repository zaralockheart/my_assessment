package my.com.assessments.adapter

import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import my.com.assessments.R
import my.com.assessments.databinding.ScheduleItemBinding
import my.com.assessments.model.Schedule
import java.util.*


class ScheduleAdapter :
    ListAdapter<Schedule, ScheduleAdapter.ViewHolder>(
        SchedulesDiffCallback()
    ) {

    inner class ViewHolder(
        private val binding: ScheduleItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(schedule: Schedule?, position: Int) {
            with(binding) {
                val calendar = Calendar.getInstance()
                // 1 is Sunday, 0 is Saturday
                calendar.set(Calendar.DAY_OF_WEEK, position + 2)
                binding.day.text = DateFormat.format("EEEE", calendar.time)
                if (schedule != null && (!schedule.engineers.isNullOrEmpty())) {
                    binding.engineerNameShift1.text = schedule.engineers[0].name
                    binding.engineerNameShift2.text = schedule.engineers[1].name
                }
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.schedule_item, parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}

private class SchedulesDiffCallback : DiffUtil.ItemCallback<Schedule>() {

    override fun areItemsTheSame(
        oldItem: Schedule,
        newItem: Schedule
    ): Boolean {
        return oldItem.engineers != newItem.engineers
    }

    override fun areContentsTheSame(
        oldItem: Schedule,
        newItem: Schedule
    ): Boolean {
        return oldItem.engineers != newItem.engineers
    }
}