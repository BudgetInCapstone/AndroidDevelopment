package com.bangkit.budgetin.repo

import android.util.Log
import com.bangkit.budgetin.api.ApiService
import com.bangkit.budgetin.api.request.SignInRequest
import com.bangkit.budgetin.api.request.SignUpRequest
import com.bangkit.budgetin.api.response.AuthResponse
import com.bangkit.budgetin.data.AuthStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthenticationRepository(
    private val apiService: ApiService,
    private val store: AuthStore,
) {
    suspend fun signUp(
        signUpRequest: SignUpRequest,
    ): AuthResponse {
        return withContext(Dispatchers.IO) {
            val response = apiService.signUp(signUpRequest)
            response.body() ?: AuthResponse(false, response.message().toString())
        }
    }

    suspend fun signIn(
        signInRequest: SignInRequest,
    ): AuthResponse {
        return withContext(Dispatchers.IO) {
            val response = apiService.signIn(signInRequest)
            if(response.isSuccessful && response.body() != null){
                store.saveUserAuth(true)
            } else {
                store.saveUserAuth(false)
            }
            response.body() ?: AuthResponse(false, response.message().toString())
        }
    }

    fun isUserAuthenticated(): Flow<Boolean>{
        return store.getUserAuth
    }

    suspend fun logout(): Boolean{
        return try {
            store.saveUserAuth(false)
            true
        }catch (_ : Exception){
            false
        }
    }
}