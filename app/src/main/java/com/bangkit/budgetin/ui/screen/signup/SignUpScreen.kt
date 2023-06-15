package com.bangkit.budgetin.ui.screen.signup

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.budgetin.api.request.SignInRequest
import com.bangkit.budgetin.api.request.SignUpRequest
import com.bangkit.budgetin.api.response.AuthResponse
import com.bangkit.budgetin.ui.ContextViewModelFactory
import com.bangkit.budgetin.ui.ViewModelFactory
import com.bangkit.budgetin.ui.components.AuthDivider
import com.bangkit.budgetin.ui.components.ButtonApp
import com.bangkit.budgetin.ui.components.TextInput
import com.bangkit.budgetin.ui.layout.AuthLayout
import com.bangkit.budgetin.ui.state.UiState
import com.bangkit.budgetin.ui.theme.BudgetInTheme

@Composable
fun SignUpScreen(
    navigateToSignIn: () -> Unit = {},
    signUpViewModel: SignUpViewModel = viewModel(
        factory = ContextViewModelFactory(LocalContext.current)
    ),
) {
    var loading by remember { mutableStateOf(false) }
    val context = LocalContext.current

    signUpViewModel.signUpResponse.collectAsState(null).let { response ->
        when (response.value) {
            is UiState.Error -> {
                loading = false
                Toast.makeText(context, "Failed to register user", Toast.LENGTH_SHORT)
            }
            is UiState.Loading -> {
                loading = true
            }
            is UiState.Success -> {
                loading = false
                navigateToSignIn()
            }
            else -> {
                loading = false
            }
        }
    }
    SignUpContent(
        navigateToSignIn = navigateToSignIn,
        signUp = signUpViewModel::signUp,
        loading = loading
    )
}


@Composable
fun SignUpContent(
    modifier: Modifier = Modifier,
    navigateToSignIn: () -> Unit = {},
    signUp: (signUpRequest: SignUpRequest) -> Unit = {},
    loading: Boolean = false,
) {
    val context = LocalContext.current
    val signUpForm = rememberSaveable {
        mutableStateOf(SignUpRequest())
    }

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
                value = signUpForm.value.nama,
                onValueChange = {
                    signUpForm.value = signUpForm.value.copy(nama = it)
                },
                inputValidation = { name ->
                    if (name.isEmpty()) "This field is required"
                    else null
                },
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )
            Text(text = "Username", style = MaterialTheme.typography.h3)
            TextInput(
                leadingIcon = Icons.Filled.Person,
                placeHolder = "Username",
                value = signUpForm.value.username,
                onValueChange = {
                    signUpForm.value = signUpForm.value.copy(username = it)
                },
                inputValidation = { username ->
                    if (username.isEmpty()) "This field is required"
                    else null
                },
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
            )
            Text(text = "Email", style = MaterialTheme.typography.h3)
            TextInput(
                leadingIcon = Icons.Default.Email,
                placeHolder = "Email",
                value = signUpForm.value.email,
                onValueChange = {
                    signUpForm.value = signUpForm.value.copy(email = it)
                },
                inputValidation = { email ->
                    if (email.isEmpty()) "This field is required"
                    else if (!Patterns.EMAIL_ADDRESS.matcher(email)
                            .matches()
                    ) "That's not valid email"
                    else null
                },
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )
            Text(text = "Password", style = MaterialTheme.typography.h3)
            TextInput(
                leadingIcon = Icons.Default.Lock,
                placeHolder = "Password",
                value = signUpForm.value.password,
                onValueChange = {
                    signUpForm.value = signUpForm.value.copy(password = it)
                },
                inputValidation = { password ->
                    if (password.isEmpty()) "This field is required"
                    else if (password.length < 8) "Password at least have 8 characters"
                    else null
                },
                isPassword = true,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
            )

            ButtonApp(
                text = if (loading) "Loading" else "register",
                modifier = Modifier
                    .padding(top = 24.dp),
                enabled = !loading,
                onClick = {
                    if (validateInput(signUpForm.value))
                        signUp(signUpForm.value)
                    else Toast.makeText(
                        context,
                        "Please fill all field in the right manner",
                        Toast.LENGTH_LONG
                    ).show()
                }
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
    BudgetInTheme {
        SignUpContent()
    }
}

fun validateInput(signUpForm: SignUpRequest): Boolean {
    return if (signUpForm.email.isEmpty()) false
    else if (signUpForm.username.isEmpty()) false
    else if (signUpForm.nama.isEmpty()) false
    else signUpForm.password.isNotEmpty()
}