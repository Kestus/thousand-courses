package ru.kestus.thousand_courses

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.kestus.core.domain.preferences.PreferencesStorage
import ru.kestus.presentation.CoursesActivity
import ru.kestus.presentation.OnboardingActivity
import ru.kestus.thousand_courses.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var preferencesStorage: PreferencesStorage

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

        if (!isLoggedIn()) {
            startActivityAndFinish(OnboardingActivity::class.java)
        } else {
            startActivityAndFinish(CoursesActivity::class.java)
        }

    }

    private fun isLoggedIn(): Boolean {
        val session = preferencesStorage.get(PreferencesStorage.KEY_SESSION)
        return session != null
    }

    private fun startActivityAndFinish(cls: Class<out AppCompatActivity>) {
        val intent = Intent(baseContext, cls)
        startActivity(intent)
        finish()
    }
}