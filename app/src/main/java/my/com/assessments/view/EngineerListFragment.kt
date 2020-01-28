package my.com.assessments.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import kotlinx.coroutines.*
import my.com.assessments.R
import my.com.assessments.adapter.EngineerListAdapter
import my.com.assessments.databinding.EngineerListFragmentBinding
import my.com.assessments.model.Engineer
import my.com.assessments.utilities.Injectors
import my.com.assessments.viewmodel.EngineerListViewModel
import retrofit2.HttpException

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
        lifecycleScope.launch {
            try {
                binding.isLoading = true
                viewModel.getEngineer()
            } catch (e: HttpException) {
                AlertDialog.Builder(context!!)
                    .setTitle(getString(R.string.error_fetching))
                    .setMessage(getString(R.string.please_check))
                    .setPositiveButton(android.R.string.yes) { dialog, _ -> dialog.cancel() }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
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
