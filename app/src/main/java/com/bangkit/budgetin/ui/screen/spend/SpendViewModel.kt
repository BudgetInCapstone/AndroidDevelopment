package com.bangkit.budgetin.ui.screen.spend

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.bangkit.budgetin.api.item.ReportItem

class SpendViewModel: ViewModel() {
    private val _reportPost = mutableStateListOf<ReportItem>()
    val reportPost: List<ReportItem> = _reportPost

    init {
        _reportPost.add(ReportItem())
    }

    fun updateReport(reportItem: ReportItem, index: Int){
        _reportPost[index] = reportItem
    }

    fun addReport(){
        _reportPost.add(ReportItem())
    }
}