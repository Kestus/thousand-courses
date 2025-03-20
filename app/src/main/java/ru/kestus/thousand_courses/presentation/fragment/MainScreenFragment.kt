package ru.kestus.thousand_courses.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import ru.kestus.thousand_courses.databinding.FragmentMainScreenBinding

class MainScreenFragment : Fragment() {
    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("MainScreenFragment == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(layoutInflater)
        return binding.main
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupBottomBarNavigation()
    }

    private fun setupBottomBarNavigation() {
        val navController = Navigation.findNavController(requireActivity(), binding.fragmentContainerMainScreen.id)
        NavigationUI.setupWithNavController(binding.bottomNavBar, navController)
    }

    companion object {
        fun newInstance() = MainScreenFragment()
    }
}