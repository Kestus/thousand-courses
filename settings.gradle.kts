pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "thousand-courses"
include(":app")
include(":core")
include(":onboarding")
include(":onboarding:presentation")
include(":courses")
include(":courses:courses_presentation")
include(":design")
include(":courses:courses_domain")
include(":courses:courses_data")
include(":courses:courses_di")
include(":network")
