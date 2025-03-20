package ru.kestus.thousand_courses.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.kestus.presentation.OnboardingActivity
import ru.kestus.thousand_courses.databinding.ActivityMainBinding
import ru.kestus.thousand_courses.presentation.fragment.MainScreenFragment
import ru.kestus.thousand_courses.presentation.viewModel.MainViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        // make bottom navigation bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
        // default padding "systemBars.bottom" adds too much padding to bottom nav bar
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        observeSession()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            viewModel.loadSession()
        }
    }

    private fun observeSession() {
        viewModel.session.observe(this) {
            val value = it
            if (value == null) {
                startActivity(OnboardingActivity::class.java)
            } else {
                supportFragmentManager.commit {
                    add(binding.fragmentContainerMain.id, MainScreenFragment.newInstance())
                }
            }
        }
    }

    private fun startActivity(cls: Class<out AppCompatActivity>) {
        val intent = Intent(baseContext, cls)
        startActivity(intent)
    }
}