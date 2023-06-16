package com.bangkit.budgetin.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.budgetin.repo.AuthenticationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: AuthenticationRepository): ViewModel() {
    private val _isLogout = MutableStateFlow(false)
    val isLogout: StateFlow<Boolean> get() = _isLogout

    fun logout() {
        viewModelScope.launch {
            _isLogout.value = repository.logout()
        }
    }
}