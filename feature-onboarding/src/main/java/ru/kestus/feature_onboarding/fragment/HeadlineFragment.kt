package ru.kestus.feature_onboarding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.kestus.feature_onboarding.R
import ru.kestus.feature_onboarding.databinding.FragmentHeadlineBinding

class HeadlineFragment : Fragment() {
    private var _binding: FragmentHeadlineBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentHeadlineBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeadlineBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_headlineFragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = HeadlineFragment()
    }
}