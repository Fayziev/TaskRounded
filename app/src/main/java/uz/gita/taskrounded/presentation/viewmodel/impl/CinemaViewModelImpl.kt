package uz.gita.taskrounded.presentation.viewmodel.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.taskrounded.data.repository.impl.CinemaRepositoryImpl
import uz.gita.taskrounded.data.response.ResultsItem
import uz.gita.taskrounded.presentation.viewmodel.CinemaViewModel
import uz.gita.taskrounded.utils.isConnected

class CinemaViewModelImpl : ViewModel(), CinemaViewModel {
    private val repository = CinemaRepositoryImpl()

    override val errorMessageLiveData = MutableLiveData<String>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val connectionLiveData = MutableLiveData<String>()
    override val allDataLiveData = MutableLiveData<List<ResultsItem>>()
    override val deleteLiveData = MutableLiveData<Unit>()

//    override fun getAllData() {
//        if (!isConnected()) {
//            connectionLiveData.value = "The Internet is not available"
//            return
//        }
//        progressLiveData.value = true
//        repository.getAllData().onEach {
//            progressLiveData.value = false
//            it.onSuccess { list ->
//                allDataLiveData.value = list
//                Log.d("TTT", "getAllData: $list")
//            }
//            it.onFailure { throwable ->
//                errorMessageLiveData.value = throwable.message
//            }
//        }.catch { throwable ->
//            errorMessageLiveData.value = throwable.message
//        }.launchIn(viewModelScope)
//    }

    override fun getAllDataPage(page: Int) {
        if (!isConnected()) {
            connectionLiveData.value = "The Internet is not available"
            return
        }
        progressLiveData.value = true
        repository.getAllDataPage(page).onEach {
            progressLiveData.value = false
            it.onSuccess { list ->
                allDataLiveData.value = list
            }
            it.onFailure { throwable ->
                errorMessageLiveData.value = throwable.message
            }
        }.catch { throwable ->
            errorMessageLiveData.value = throwable.message
        }.launchIn(viewModelScope)

    }
}