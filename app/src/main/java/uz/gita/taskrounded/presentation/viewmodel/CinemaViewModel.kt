package uz.gita.taskrounded.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.taskrounded.data.response.InfoData
import uz.gita.taskrounded.data.response.ResultsItem

interface CinemaViewModel {
    val errorMessageLiveData: LiveData<String>
    val progressLiveData: LiveData<Boolean>
    val connectionLiveData: LiveData<String>
    val allDataLiveData: LiveData<List<ResultsItem>>
    val deleteLiveData: LiveData<Unit>

//    fun getAllData()
    fun getAllDataPage(page:Int)
}