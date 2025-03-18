package ru.kestus.thousand_courses

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.kestus.data.PreferencesStorageImpl
import ru.kestus.domain.PreferencesStorage
import ru.kestus.feature_onboarding.OnboardingActivity
import ru.kestus.thousand_courses.databinding.ActivityMainBinding
import ru.kestus.thousand_courses.mainActivity.CoursesActivity

class MainActivity : AppCompatActivity() {

    private lateinit var preferencesStorage: PreferencesStorage
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
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