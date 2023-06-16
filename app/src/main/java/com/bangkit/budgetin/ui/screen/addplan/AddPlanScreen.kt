package com.bangkit.budgetin.ui.screen.addplan

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.budgetin.api.request.RecommendationRequest
import com.bangkit.budgetin.common.Coordinates
import com.bangkit.budgetin.ui.itemview.Repeat
import com.bangkit.budgetin.ui.ViewModelFactory
import com.bangkit.budgetin.ui.components.ButtonApp
import com.bangkit.budgetin.ui.components.Dropdown
import com.bangkit.budgetin.ui.components.TextInput
import com.bangkit.budgetin.ui.itemview.PlanItem
import com.bangkit.budgetin.ui.theme.BudgetInTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddPlanScreen(
    navigateToRecommendation: () -> Unit = {},
    addPlanViewModel: AddPlanViewModel = viewModel(
        factory = ViewModelFactory()
    ),
) {
    CreateSpendPlanContent(
        navigateToRecommendation = navigateToRecommendation,
        generateRecommendation = addPlanViewModel::generateRecommendation
    )
}

@Composable
fun CreateSpendPlanContent(
    navigateToRecommendation: () -> Unit = {},
    generateRecommendation: (recommendationRequest: RecommendationRequest) -> Unit = {},
) {
    val spendForm = remember {
        mutableStateListOf(PlanItem())
    }
    val initialIncome = remember {
        mutableStateOf("")
    }
    val coordinates = remember {
        mutableStateOf<Coordinates?>(null)
    }
    val isRequested = remember { mutableStateOf(false) }

    val context = LocalContext.current
    val fusedLocation = remember { LocationServices.getFusedLocationProviderClient(context) }

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionMaps ->
        val isGranted = permissionMaps.values.reduce { acc, next -> acc || next }
        if (isGranted) {
            Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(key1 = isRequested.value) {
        checkPermission(
            context,
            fusedLocation,
            launcherMultiplePermissions,
            coordinates,
            isRequested
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.AccountCircle,
                                contentDescription = "Initial Income Icon",
                                modifier = Modifier.size(32.dp)
                            )
                            Text(
                                text = "Initial Income",
                                style = MaterialTheme.typography.h6,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "Rp.",
                                style = MaterialTheme.typography.h1,
                            )
                            TextInput(
                                value = initialIncome.value,
                                onValueChange = { initialIncome.value = it },
                                placeHolder = "Your Monthly Budget",
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .weight(1F),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }
                    }
                }
                Text(
                    text = "Spend Plan",
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                // Add code for rendering spent plan cards here
                spendForm.forEachIndexed { index, _ ->
                    SpendPlanCard(
                        onNeedsTypeChange = {
                            spendForm[index] = spendForm[index].copy(type = it)
                        },
                        onNeedsRepeatChange = {
                            spendForm[index] =
                                spendForm[index].copy(
                                    repeat = Repeat.getNeedsRepeat(it)
                                        .copy(number = spendForm[index].repeat.number)
                                )
                        },
                        onNeedsNumberChange = {
                            spendForm[index] =
                                spendForm[index].copy(
                                    repeat = spendForm[index].repeat.copy(
                                        number = it[1].toString().toInt()
                                    )
                                )
                        },
                        onDelete = {
                            spendForm.removeAt(index)
                        }
                    )
                }
                FloatingActionButton(
                    onClick = {
                        spendForm.add(PlanItem())
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp, bottom = 32.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Spent Plan")
                }
            }
        }
        ButtonApp(
            text = "GENERATE SPEND PLAN",
            onClick = {
                if (initialIncome.value.isEmpty()) {
                    Toast.makeText(context, "Please fill your budget first", Toast.LENGTH_SHORT)
                        .show()
                    return@ButtonApp
                }
                if (coordinates.value == null) {
                    Toast.makeText(
                        context,
                        "Please allow location permission first",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    return@ButtonApp
                }

                val spendNeeds = spendForm.sumOf {
                    when (it.repeat.needs) {
                        Repeat.Daily.needs -> it.repeat.number * 31
                        Repeat.Weekly.needs -> it.repeat.number * 4
                        else -> it.repeat.number
                    }
                }

                val listRecommendationRequest = (1..3).map {
                    RecommendationRequest(
                        max = initialIncome.value.toLong() / spendNeeds,
                        min = 0,
                        lat = coordinates.value?.lat,
                        long = coordinates.value?.long,
                        radius = 2 * it
                    )
                }
                listRecommendationRequest.forEach {
                    generateRecommendation(it)
                    navigateToRecommendation()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
    }
}

@Composable
fun SpendPlanCard(
    onNeedsTypeChange: (type: String) -> Unit,
    onNeedsRepeatChange: (repeat: String) -> Unit,
    onNeedsNumberChange: (number: String) -> Unit,
    onDelete: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = "Expense Icon",
                modifier = Modifier.size(32.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1F),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Dropdown(
                    options = listOf("Food"),
                    initialValue = "Food",
                    onValueChange = onNeedsTypeChange,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Dropdown(
                        options = listOf(
                            Repeat.Daily.needs,
                            Repeat.Weekly.needs,
                            Repeat.Monthly.needs
                        ),
                        initialValue = Repeat.Daily.needs,
                        onValueChange = onNeedsRepeatChange,
                        modifier = Modifier.weight(1F)
                    )
                    Dropdown(
                        options = (1..9).map { "x$it" },
                        initialValue = "x1",
                        onValueChange = onNeedsNumberChange,
                        modifier = Modifier.weight(1F)
                    )
                }
            }
            IconButton(
                onClick = onDelete,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Remove Spend Plan",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddPlanScreenPreview() {
    BudgetInTheme {
        AddPlanScreen()
    }
}

private fun checkPermission(
    context: Context,
    fusedLocation: FusedLocationProviderClient,
    launcherMultiplePermissions: ManagedActivityResultLauncher<Array<String>, Map<String, @JvmSuppressWildcards Boolean>>,
    coordinates: MutableState<Coordinates?>,
    isRequested: MutableState<Boolean>,
) {
    val granted = ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    if (granted) {
        fusedLocation.lastLocation.addOnSuccessListener { location: Location? ->
            isRequested.value = false
            if (location != null)
                coordinates.value = Coordinates(location.latitude, location.longitude)
        }
    } else {
        isRequested.value = true
        launcherMultiplePermissions.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
    }
}