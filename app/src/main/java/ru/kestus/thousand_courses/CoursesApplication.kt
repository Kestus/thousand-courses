package ru.kestus.thousand_courses

import android.app.Application
import ru.kestus.thousand_courses.di.DaggerApplicationComponent

class CoursesApplication: Application() {

    // create component
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        // inject this
        super.onCreate()
    }

}