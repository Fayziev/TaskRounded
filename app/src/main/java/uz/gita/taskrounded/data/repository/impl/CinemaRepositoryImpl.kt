package uz.gita.taskrounded.data.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.taskrounded.data.ApiClient
import uz.gita.taskrounded.data.api.CinemaApi
import uz.gita.taskrounded.data.repository.CinemaRepository
import uz.gita.taskrounded.data.response.ResultsItem

class CinemaRepositoryImpl : CinemaRepository {
    private val api = ApiClient.retrofit.create(CinemaApi::class.java)

//    override fun getAllData(): Flow<Result<List<ResultsItem>>> = flow {
//        val response = api.getAllData()
//        if (response.isSuccessful) {
//            emit(Result.success(response.body()!!.results))
//        } else {
//            val message = "Xatolik yuzaga keldi!"
//            response.errorBody()?.let {
//                emit(Result.failure<List<ResultsItem>>(Throwable(message)))
//            }
//        }
//    }.flowOn(Dispatchers.IO)

    override fun getAllDataPage(page: Int): Flow<Result<List<ResultsItem>>> = flow {
        val response = api.getAllDataPage(page)
        if (response.isSuccessful) {
            emit(Result.success(response.body()!!.results))
        } else {
            val message = "Xatolik yuzaga keldi!"
            response.errorBody()?.let {
                emit(Result.failure<List<ResultsItem>>(Throwable(message)))
            }
        }
    }.flowOn(Dispatchers.IO)

}
