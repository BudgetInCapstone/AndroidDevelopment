package com.bangkit.budgetin.ui.screen.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.budgetin.api.request.SignInRequest
import com.bangkit.budgetin.api.request.SignUpRequest
import com.bangkit.budgetin.api.response.AuthResponse
import com.bangkit.budgetin.repo.AuthenticationRepository
import com.bangkit.budgetin.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(private val repository: AuthenticationRepository) : ViewModel() {
    private val _signUpResponse: MutableStateFlow<UiState<AuthResponse>?> =
        MutableStateFlow(null)
    val signUpResponse: StateFlow<UiState<AuthResponse>?>
        get() = _signUpResponse

    fun signUp(signUpRequest: SignUpRequest){
        _signUpResponse.value = UiState.Loading
        viewModelScope.launch {
            try {
                _signUpResponse.value = UiState.Success(repository.signUp(signUpRequest))
            } catch (_: Exception) {
                _signUpResponse.value = UiState.Error("error")
            }
        }
    }
}