package com.bangkit.budgetin.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.budgetin.di.Injection
import com.bangkit.budgetin.ui.screen.addplan.AddPlanViewModel
import com.bangkit.budgetin.ui.screen.signin.SignInViewModel
import com.bangkit.budgetin.ui.screen.signup.SignUpViewModel
import com.bangkit.budgetin.ui.screen.spend.SpendViewModel

class ViewModelFactory :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpendViewModel::class.java)) {
            return SpendViewModel() as T
        } else if (modelClass.isAssignableFrom(AddPlanViewModel::class.java)) {
            return AddPlanViewModel(Injection.provideRecommendationRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}