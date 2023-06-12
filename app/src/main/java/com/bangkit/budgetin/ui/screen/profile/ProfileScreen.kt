package com.bangkit.budgetin.ui.screen.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.budgetin.R
import com.bangkit.budgetin.ui.components.ButtonApp
import com.bangkit.budgetin.ui.components.ProfileItemView
import com.bangkit.budgetin.ui.itemview.ProfileGroupItem
import com.bangkit.budgetin.ui.itemview.ProfileItem
import com.bangkit.budgetin.ui.theme.BudgetInTheme
import com.bangkit.budgetin.ui.theme.Teal400

@Composable
fun ProfileScreen(
    navigateToSignin: () -> Unit = {}
) {
    ProfileContent(
        navigateToSignin =  navigateToSignin
    )
}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    navigateToSignin: () -> Unit = {}
) {
    val profileItemList = listOf(
        ProfileGroupItem(
            groupName = "Account",
            profileItems = listOf(
                ProfileItem(
                    title = "Profile Detail",
                    image = painterResource(id = R.drawable.ic_profile_detail)
                ),
                ProfileItem(
                    title = "Account Detail",
                    image = painterResource(id = R.drawable.ic_account)
                ),
                ProfileItem(
                    title = "Change Password",
                    image = painterResource(id = R.drawable.ic_password)
                ),
            )
        ),
        ProfileGroupItem(
            groupName = "Settings",
            profileItems = listOf(
                ProfileItem(
                    title = "Language",
                    image = painterResource(id = R.drawable.ic_language)
                ),
                ProfileItem(
                    title = "Theme",
                    image = painterResource(id = R.drawable.ic_theme)
                ),
            )
        ),
        ProfileGroupItem(
            groupName = "Others",
            profileItems = listOf(
                ProfileItem(
                    title = "Privacy Policies",
                    image = painterResource(id = R.drawable.ic_privacy)
                ),
                ProfileItem(
                    title = "Term and Conditions",
                    image = painterResource(id = R.drawable.ic_term)
                ),
            )
        )
    )

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        ProfileItemView(
            title = "User123",
            description = "user123@gmail.com",
            image = painterResource(id = R.drawable.ic_profile),
            modifier = Modifier
                .padding(horizontal = 24.dp)
        )

        profileItemList.map { profileGroup ->
            Text(
                text = profileGroup.groupName,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 24.dp)
            )
            profileGroup.profileItems.mapIndexed { index, profileItem ->
                if (index != 0) {
                    Divider(
                        modifier = Modifier
                            .padding(vertical = 12.dp, horizontal = 24.dp)
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(Teal400)
                    )
                }
                ProfileItemView(
                    modifier = Modifier
                        .padding(top = if (index == 0) 16.dp else 0.dp)
                        .padding(horizontal = 24.dp),
                    title = profileItem.title,
                    description = profileItem.description,
                    image = profileItem.image,
                    circle = false
                )
            }
        }

        ButtonApp(
            text = "logout",
            modifier = Modifier
                .padding(24.dp),
            onClick = navigateToSignin
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2_XL)
@Composable
fun ProfilePreview() {
    BudgetInTheme {
        ProfileContent(
            Modifier.fillMaxSize()
        )
    }
}