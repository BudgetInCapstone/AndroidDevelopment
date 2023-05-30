package com.bangkit.budgetin.ui.screen.signin

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.budgetin.R
import com.bangkit.budgetin.ui.components.AuthDivider
import com.bangkit.budgetin.ui.components.ButtonApp
import com.bangkit.budgetin.ui.components.SignInBox
import com.bangkit.budgetin.ui.components.TextInput
import com.bangkit.budgetin.ui.layout.AuthLayout
import com.bangkit.budgetin.ui.theme.BudgetInTheme

@Composable
fun SignInScreen(
    navigateToSignUp: () -> Unit = {},
) {
    SignInContent(navigateToSignUp = navigateToSignUp)
}

@Composable
fun SignInContent(
    modifier: Modifier = Modifier,
    navigateToSignUp: () -> Unit = {},
) {
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

            Text(
                text = "Forgot Password?",
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.align(Alignment.End)
            )

            ButtonApp(
                text = "login",
                modifier = Modifier.padding(top = 24.dp)
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