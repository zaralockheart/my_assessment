package my.com.assessments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import my.com.assessments.R
import my.com.assessments.databinding.EngineerItemBinding
import my.com.assessments.model.EngineerX

class EngineerListAdapter :
    ListAdapter<EngineerX, EngineerListAdapter.ViewHolder>(
        EngineerDiffCallback()
    ) {

    inner class ViewHolder(
        private val binding: EngineerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(engineer: EngineerX?) {
            with(binding) {
                this.engineer = engineer
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.engineer_item, parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.bind(null)
        } else {
            holder.bind(getItem(position - 1))
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }
}

private class EngineerDiffCallback : DiffUtil.ItemCallback<EngineerX>() {

    override fun areItemsTheSame(
        oldItem: EngineerX,
        newItem: EngineerX
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: EngineerX,
        newItem: EngineerX
    ): Boolean {
        return oldItem.id == newItem.id
    }
}