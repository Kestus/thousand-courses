package ru.kestus.network

import retrofit2.http.GET
import ru.kestus.network.model.CoursesResponseDao

interface ApiService {

    @GET("u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    suspend fun getCourses(): CoursesResponseDao

}