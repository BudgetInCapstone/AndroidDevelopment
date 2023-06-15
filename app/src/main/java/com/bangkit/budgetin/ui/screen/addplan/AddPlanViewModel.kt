package com.bangkit.budgetin.ui.screen.addplan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.budgetin.api.request.RecommendationRequest
import com.bangkit.budgetin.api.response.RecommendationResponse
import com.bangkit.budgetin.repo.RecommendationRepository
import com.bangkit.budgetin.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AddPlanViewModel(
    private val repository: RecommendationRepository
): ViewModel() {
    private val _recommendationResponse: MutableStateFlow<UiState<RecommendationResponse>> =
        MutableStateFlow(UiState.Loading)
    val recommendationResponse: StateFlow<UiState<RecommendationResponse>>
        get() = _recommendationResponse

    fun generateRecommendation(recommendationRequest: RecommendationRequest){
        viewModelScope.launch {
            repository.generateRecommendation(recommendationRequest)
                .catch {
                    _recommendationResponse.value = UiState.Error(it.message.toString())
                }
                .collect{response ->
                    _recommendationResponse.value = UiState.Success(response)
                }
        }
    }
}