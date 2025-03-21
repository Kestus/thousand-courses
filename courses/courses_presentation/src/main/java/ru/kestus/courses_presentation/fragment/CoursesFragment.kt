package ru.kestus.courses_presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.kestus.courses_presentation.CoursesFragmentState
import ru.kestus.courses_presentation.adapter.AdapterDelegate
import ru.kestus.courses_presentation.adapter.RecyclerItem
import ru.kestus.courses_presentation.viewModel.CoursesViewModel
import ru.kestus.presentation.databinding.FragmentCoursesBinding
import javax.inject.Inject

@AndroidEntryPoint
class CoursesFragment : Fragment() {

    private var _binding: FragmentCoursesBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentCoursesBinding == null")

    private val viewModel: CoursesViewModel by viewModels()

    @Inject
    lateinit var adapter: AdapterDelegate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoursesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupRecyclerView()
        collectStateFlow()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAdapter() {
        adapter.setOnCourseFavButtonClickListener {
            viewModel.toggleFavoriteStatus(it)
        }
        adapter.setOnCourseMoreButtonClickListener {
            Log.d(TAG, "setupRecyclerView: #${it.id} button clicked")
        }
    }

    private fun setupRecyclerView() {
        binding.rvCourses.adapter = adapter
    }

    private fun collectStateFlow() {
        lifecycleScope.launch {
            viewModel.stateFlow.collect {
                when (it) {
                    CoursesFragmentState.Initial -> {
                        Log.d(TAG, "collectStateFlow: flow started")
                    }
                    CoursesFragmentState.Loading -> {
                        adapter.items = emptyList<RecyclerItem>()
                        Log.d(TAG, "collectStateFlow: Loading started")
                    }
                    is CoursesFragmentState.Content -> {
                        adapter.items = it.value
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "CoursesFragment"
    }

}