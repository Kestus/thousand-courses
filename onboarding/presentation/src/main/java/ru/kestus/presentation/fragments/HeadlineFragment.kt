package ru.kestus.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.kestus.presentation.R
import ru.kestus.presentation.databinding.FragmentHeadlineBinding

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
        setContinueButtonOnClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setContinueButtonOnClickListener() {
        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_headlineFragment_to_loginFragment)
        }
    }

    companion object {
        fun newInstance() = HeadlineFragment()
    }
}