package com.bangkit.budgetin.ui.screen.signin

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.budgetin.R
import com.bangkit.budgetin.api.request.SignInRequest
import com.bangkit.budgetin.data.AuthStore
import com.bangkit.budgetin.ui.ContextViewModelFactory
import com.bangkit.budgetin.ui.ViewModelFactory
import com.bangkit.budgetin.ui.components.AuthDivider
import com.bangkit.budgetin.ui.components.ButtonApp
import com.bangkit.budgetin.ui.components.SignInBox
import com.bangkit.budgetin.ui.components.TextInput
import com.bangkit.budgetin.ui.layout.AuthLayout
import com.bangkit.budgetin.ui.state.UiState
import com.bangkit.budgetin.ui.theme.BudgetInTheme

@Composable
fun SignInScreen(
    navigateToSignUp: () -> Unit = {},
    navigateToHome: () -> Unit = {},
    signInViewModel: SignInViewModel = viewModel(
        factory = ContextViewModelFactory(LocalContext.current)
    ),
) {
    val loading = remember { mutableStateOf(false) }
    val context = LocalContext.current

    signInViewModel.signInResponse.collectAsState(null).value.let {
        when (it) {
            is UiState.Error -> {
                loading.value = false
                Toast.makeText(context, "Network error", Toast.LENGTH_SHORT)
            }
            is UiState.Loading -> {
                loading.value = true
            }
            is UiState.Success -> {
                loading.value = false
                if (it.data.success)
                    navigateToHome()
                else Toast.makeText(context, "Failed to login", Toast.LENGTH_SHORT)
            }
            else -> {
                loading.value = false
            }
        }
    }
    signInViewModel.isUserAutheticated().collectAsState(initial = false).value.let {
        if(it) navigateToHome()
    }

    SignInContent(
        navigateToSignUp = navigateToSignUp,
        signIn = signInViewModel::signIn,
        loading = loading.value
    )
}

@Composable
fun SignInContent(
    modifier: Modifier = Modifier,
    navigateToSignUp: () -> Unit = {},
    signIn: (signInRequest: SignInRequest) -> Unit = {},
    loading: Boolean = false,
) {
    val context = LocalContext.current
    val signInForm = rememberSaveable {
        mutableStateOf(SignInRequest())
    }

    AuthLayout(
        title = "Sign In to Your Account",
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
        ) {
            Text(text = "Email", style = MaterialTheme.typography.h3)
            TextInput(
                leadingIcon = Icons.Default.Email,
                placeHolder = "Email",
                value = signInForm.value.email,
                onValueChange = {
                    signInForm.value = signInForm.value.copy(email = it)
                },
                inputValidation = {
                    if (it.isEmpty()) "This field is required"
                    else if (!Patterns.EMAIL_ADDRESS.matcher(it).matches()) "That's not valid email"
                    else null
                },
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Text(text = "Password", style = MaterialTheme.typography.h3)
            TextInput(
                leadingIcon = Icons.Default.Lock,
                placeHolder = "Password",
                value = signInForm.value.password,
                onValueChange = {
                    signInForm.value = signInForm.value.copy(password = it)
                },
                inputValidation = { password ->
                    if (password.length < 8)
                        "Password at least have 8 characters"
                    else null
                },
                isPassword = true,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Text(
                text = "Forgot Password?",
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.align(Alignment.End)
            )

            ButtonApp(
                text = if (loading) "Loading" else "Login",
                modifier = Modifier
                    .padding(top = 24.dp),
                enabled = !loading,
                onClick = {
                    if (validateInput(signInForm.value))
                        signIn(signInForm.value)
                    else
                        Toast.makeText(
                            context,
                            "Please fill all field with the right manner",
                            Toast.LENGTH_LONG
                        ).show()
                }
            )
            AuthDivider(
                text = "or login with"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SignInBox(
                    painter = painterResource(id = R.drawable.ic_google)
                )
                SignInBox(
                    painter = painterResource(id = R.drawable.ic_google)
                )
                SignInBox(
                    painter = painterResource(id = R.drawable.ic_google)
                )
            }

            AuthDivider(text = "Don't have an account?")

            ButtonApp(
                text = "register",
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(bottom = 24.dp),
                onClick = navigateToSignUp
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFE7E7E7,
    device = Devices.PIXEL_2_XL,
)
@Composable
fun SignInPreview() {
    BudgetInTheme {
        SignInContent()
    }
}

fun validateInput(value: SignInRequest): Boolean {
    return if (value.email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value.email).matches()) false
    else value.password.length >= 8
}