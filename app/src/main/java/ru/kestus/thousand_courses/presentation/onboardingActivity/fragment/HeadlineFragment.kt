package ru.kestus.thousand_courses.presentation.onboardingActivity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.kestus.thousand_courses.R
import ru.kestus.thousand_courses.databinding.FragmentHeadlineBinding


class HeadlineFragment : Fragment() {

    private var _binding: FragmentHeadlineBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("HeadlineFragmentBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeadlineBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnContinue.setOnClickListener {
            launchRegisterFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchRegisterFragment() {
        findNavController().navigate(R.id.action_headlineFragment_to_registerFragment)
    }

    companion object {
        fun newInstance() = HeadlineFragment()
    }
}