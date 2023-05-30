package com.bangkit.budgetin.ui.screen.signup

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.budgetin.ui.components.AuthDivider
import com.bangkit.budgetin.ui.components.ButtonApp
import com.bangkit.budgetin.ui.components.TextInput
import com.bangkit.budgetin.ui.layout.AuthLayout

@Composable
fun SignUpScreen(
    navigateToSignIn: () -> Unit = {},
) {
    SignUpContent(navigateToSignIn = navigateToSignIn)
}


@Composable
fun SignUpContent(
    modifier: Modifier = Modifier,
    navigateToSignIn: () -> Unit = {},
) {
    AuthLayout(
        title = "Sign Up to Your Account",
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
        ) {
            Text(text = "Name", style = MaterialTheme.typography.h3)
            TextInput(
                leadingIcon = Icons.Filled.Edit,
                placeHolder = "Name",
                value = "",
                onValueChange = {},
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(text = "Username", style = MaterialTheme.typography.h3)
            TextInput(
                leadingIcon = Icons.Filled.Person,
                placeHolder = "Username",
                value = "",
                onValueChange = {},
                modifier = Modifier.padding(bottom = 16.dp),
            )
            Text(text = "Email", style = MaterialTheme.typography.h3)
            TextInput(
                leadingIcon = Icons.Default.Email,
                placeHolder = "Email",
                value = "",
                onValueChange = {},
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(text = "Password", style = MaterialTheme.typography.h3)
            TextInput(
                leadingIcon = Icons.Default.Lock,
                placeHolder = "Password",
                value = "",
                onValueChange = {},
                isPassword = true,
                modifier = Modifier.padding(bottom = 16.dp),
            )

            ButtonApp(
                text = "register",
                modifier = Modifier.padding(top = 24.dp)
            )
            AuthDivider(
                text = "have an account?"
            )

            ButtonApp(
                text = "login",
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(bottom = 24.dp),
                onClick = navigateToSignIn
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    SignUpContent()
}