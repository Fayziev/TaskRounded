package uz.gita.taskrounded.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.taskrounded.data.response.InfoData

interface CinemaApi {

    @GET("character")
    suspend fun getAllData(): Response<InfoData>

    @GET("character")
    suspend fun getAllDataPage(
        @Query("page") page: Int
    ): Response<InfoData>


}