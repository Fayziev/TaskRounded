package uz.gita.taskrounded.data.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.taskrounded.data.response.ResultsItem

interface CinemaRepository {

//    fun getAllData(): Flow<Result<List<ResultsItem>>>
    fun getAllDataPage(page: Int): Flow<Result<List<ResultsItem>>>
}