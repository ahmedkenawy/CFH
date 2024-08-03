package com.ahmedkenawy.cfhtest.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedkenawy.cfhtest.domain.model.User
import com.ahmedkenawy.cfhtest.domain.use_case.main_use_case.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ProfileViewModel manages the data for the profile screen.
 *
 * This ViewModel uses dependency injection to obtain the MainUseCase, which provides
 * the necessary use cases for fetching user data. It exposes a LiveData object containing
 * the user's information and initializes it by fetching the user data from the use case.
 * The ViewModel uses Kotlin Coroutines for asynchronous operations and LiveData for
 * observing changes in the user data.
 *
 * @param mainUseCase The use case for fetching user data, injected by Hilt.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val mainUseCase: MainUseCase
) : ViewModel() {

    // LiveData for holding user information
    private val _userInfo = MutableLiveData<User?>()
    val userInfo: LiveData<User?> get() = _userInfo

    /**
     * Initializes the ViewModel by fetching user information from the use case.
     * This is done in a coroutine launched in the viewModelScope.
     *
     * The user information is fetched as a flow and converted to a value using the
     * `first()` operator. The fetched user data is then set to the LiveData.
     */
    init {
        viewModelScope.launch {
            val user = mainUseCase.profileUseCase.userInfo.first()
            _userInfo.value = user
            Log.d("TAG", "Kenawy will print:${userInfo.value?.email} ")
        }
    }
}
