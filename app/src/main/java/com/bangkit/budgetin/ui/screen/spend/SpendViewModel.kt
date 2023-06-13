package com.bangkit.budgetin.ui.screen.spend

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.bangkit.budgetin.api.request.ReportRequest

class SpendViewModel: ViewModel() {
    private val _reportPost = mutableStateListOf<ReportRequest>()
    val reportPost: List<ReportRequest> = _reportPost

    init {
        _reportPost.add(ReportRequest())
    }

    fun updateReport(reportItem: ReportRequest, index: Int){
        _reportPost[index] = reportItem
    }

    fun addReport(){
        _reportPost.add(ReportRequest())
    }
}