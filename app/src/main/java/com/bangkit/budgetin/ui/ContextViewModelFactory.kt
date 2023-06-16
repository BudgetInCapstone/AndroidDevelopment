package com.bangkit.budgetin.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.budgetin.di.Injection
import com.bangkit.budgetin.ui.screen.profile.ProfileViewModel
import com.bangkit.budgetin.ui.screen.signin.SignInViewModel
import com.bangkit.budgetin.ui.screen.signup.SignUpViewModel

class ContextViewModelFactory(private val context: Context)
    : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(Injection.provideAuthRepository(context)) as T
        } else if(modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(Injection.provideAuthRepository(context)) as T
        } else if(modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(Injection.provideAuthRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}