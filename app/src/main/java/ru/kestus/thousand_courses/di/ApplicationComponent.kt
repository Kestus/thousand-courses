package ru.kestus.thousand_courses.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component


@ApplicationScope
@Component(
    modules = [

    ]
)
interface ApplicationComponent {

    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(@BindsInstance application: Application) : ApplicationComponent
    }

}