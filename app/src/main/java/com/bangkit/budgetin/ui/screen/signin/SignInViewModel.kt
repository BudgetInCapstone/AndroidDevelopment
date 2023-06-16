package com.bangkit.budgetin.ui.screen.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.budgetin.api.request.SignInRequest
import com.bangkit.budgetin.api.response.AuthResponse
import com.bangkit.budgetin.repo.AuthenticationRepository
import com.bangkit.budgetin.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignInViewModel(private val repository: AuthenticationRepository): ViewModel() {
    private val _signInResponse: MutableStateFlow<UiState<AuthResponse>?> =
        MutableStateFlow(null)
    val signInResponse: StateFlow<UiState<AuthResponse>?>
        get() = _signInResponse

    fun signIn(signInRequest: SignInRequest) {
        _signInResponse.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = repository.signIn(signInRequest)
                _signInResponse.value = UiState.Success(response)
            } catch (_: Exception) {
            }
        }
    }

    fun isUserAutheticated() = repository.isUserAuthenticated()
}