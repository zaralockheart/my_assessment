package my.com.assessments.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import my.com.assessments.R
import my.com.assessments.adapter.EngineerListAdapter
import my.com.assessments.databinding.EngineerListFragmentBinding
import my.com.assessments.model.Engineer
import my.com.assessments.utilities.Injectors
import my.com.assessments.viewmodel.EngineerListViewModel

class EngineerListFragment : Fragment() {

    private lateinit var binding: EngineerListFragmentBinding

    private val viewModel: EngineerListViewModel by viewModels {
        Injectors.provideEngineerListViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EngineerListFragmentBinding.inflate(inflater, container, false)
        val adapter = EngineerListAdapter()
        binding.engineerList.adapter = adapter

        viewModel.engineer.observe(viewLifecycleOwner) { result ->
            if (result != null) {
                adapter.submitList(result.engineers)
                binding.buttonGenerate.setOnClickListener { onClick(it, result) }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                binding.isLoading = true
                viewModel.getEngineer()
            } catch (e: Exception) {
                Log.e("TAG", e.message ?: "")
            } finally {
                binding.isLoading = false
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onClick(view: View, engineer: Engineer?) {
        val bundle = Bundle().apply {
            putParcelable(ScheduleFragment.ENGINEER, engineer)
        }
        view.findNavController()
            .navigate(R.id.action_engineerListFragment_to_scheduleFragment, bundle)
    }
}
