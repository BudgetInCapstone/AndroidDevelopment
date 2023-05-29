package com.bangkit.budgetin.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bangkit.budgetin.ui.components.AuthHeader

@Composable
fun AuthLayout(
    modifier: Modifier = Modifier,
    titleModifier: Modifier = Modifier,
    title: String = "",
    content: @Composable () -> Unit = {},
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        AuthHeader(
            modifier = titleModifier,
            text = title,
        )
        content()
    }
}