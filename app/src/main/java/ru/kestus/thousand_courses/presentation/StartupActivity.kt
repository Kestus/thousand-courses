package ru.kestus.thousand_courses.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.kestus.thousand_courses.data.PreferencesStorageImpl
import ru.kestus.thousand_courses.databinding.ActivityStartupBinding
import ru.kestus.thousand_courses.domain.PreferencesStorage
import ru.kestus.thousand_courses.presentation.mainActivity.MainActivity
import ru.kestus.thousand_courses.presentation.onboardingActivity.OnboardingActivity

class StartupActivity : AppCompatActivity() {

    private lateinit var preferencesStorage: PreferencesStorage
    private val binding by lazy {
        ActivityStartupBinding.inflate(layoutInflater)
    }

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

        preferencesStorage = PreferencesStorageImpl(baseContext)

        if (!isLoggedIn()) {
            startActivityAndFinish(OnboardingActivity::class.java)
        } else {
            startActivityAndFinish(MainActivity::class.java)
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