package ru.kestus.thousand_courses

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.kestus.presentation.CoursesActivity
import ru.kestus.presentation.OnboardingActivity
import ru.kestus.thousand_courses.databinding.ActivityMainBinding

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
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d("TAG", "onCreate: ${application.packageName}")
        observeSession()
        loadSession()
    }

    private fun observeSession() {
        viewModel.session.observe(this) {
            val value = it
            if (value == null) {
                startActivityAndFinish(OnboardingActivity::class.java)
            } else {
                startActivityAndFinish(CoursesActivity::class.java)
            }
        }
    }

    private fun loadSession() = lifecycleScope.launch {
        viewModel.loadSession()
    }

    private fun startActivityAndFinish(cls: Class<out AppCompatActivity>) {
        val intent = Intent(baseContext, cls)
        startActivity(intent)
    }
}