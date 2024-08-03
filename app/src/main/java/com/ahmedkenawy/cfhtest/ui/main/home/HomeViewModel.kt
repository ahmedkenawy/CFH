package com.ahmedkenawy.cfhtest.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedkenawy.cfhtest.domain.model.response.VenuesResponse
import com.ahmedkenawy.cfhtest.domain.use_case.main_use_case.MainUseCase
import com.ahmedkenawy.cfhtest.utils.Constants.BASE_DELAY_MILLIS
import com.ahmedkenawy.cfhtest.utils.Constants.MAX_RETRY_COUNT
import com.ahmedkenawy.cfhtest.utils.Resource
import com.ahmedkenawy.cfhtest.utils.events.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.pow

@HiltViewModel
class HomeViewModel @Inject constructor(private val mainUseCase: MainUseCase) : ViewModel() {

    private val _venuesLiveData = SingleLiveEvent<Resource<VenuesResponse>?>()
    val venuesLiveData: SingleLiveEvent<Resource<VenuesResponse>?> get() = _venuesLiveData


    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _venuesLiveData.value = Resource.Error(exception.localizedMessage ?: "No Internet Connection")
    }


    private fun getVenuesFlow(location: String): Flow<Resource<VenuesResponse>> = flow {
        emit(Resource.Loading()) // Emit loading state
        val response = mainUseCase.homeUseCase.invoke(location)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(Resource.Success(body)) // Emit success state with data
            } else {
                emit(Resource.Error("No data available")) // Emit error state if no data
            }
        } else {
            emit(Resource.Error(response.message())) // Emit error state with message
        }
    }


    fun getVenues(location: String) {
        viewModelScope.launch(exceptionHandler) {
            getVenuesFlow(location)
                .retryWhen { cause, attempt ->
                    if (attempt < MAX_RETRY_COUNT) {
                        // Calculate delay based on retry attempt
                        val delayMillis = (2.0.pow(attempt.toDouble()) * BASE_DELAY_MILLIS).toLong()
                        delay(delayMillis)
                        true // Retry fetching data
                    } else {
                        false // Stop retrying after max attempts
                    }
                }
                .catch { e ->
                    // Handle and emit error if data fetching fails
                    _venuesLiveData.value = Resource.Error(e.localizedMessage ?: "Unknown error")
                }
                .collect { result ->
                    // Emit the final result to LiveData
                    _venuesLiveData.value = result
                }
        }
    }
}
