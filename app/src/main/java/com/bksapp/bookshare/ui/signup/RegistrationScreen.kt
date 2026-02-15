package com.bksapp.bookshare.ui.signup

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bksapp.bookshare.R
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun SignUpScreen() {
    val viewModel : SignupViewModel = viewModel()
    val userDataState  = viewModel.signupState.collectAsStateWithLifecycle()
    var showDateDialog by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Input
    )

    val backColor = Brush.verticalGradient(
        listOf(Color.Magenta,Color.LightGray,Color.LightGray,Color.Green)

    )

    val scrollState = rememberScrollState()


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(brush = backColor)
                .imePadding()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {

            CommonRow{
                OutlinedTextField(
                    value = userDataState.value.name,
                    onValueChange = { name ->
                        viewModel.updateName(name)
                    },
                    label = { Text("name") },
                    isError = !userDataState.value.isValidName,
                    supportingText = {
                        if(!userDataState.value.isValidName) {
                            Text("Please Enter valid name")
                        }
                        else{Text(text = "")}
                    }

                )
            }

            CommonRow {
                OutlinedTextField(
                    value = userDataState.value.email,
                    onValueChange = { email ->
                        viewModel.updateEmail(email)
                    },
                    label = {Text("Email")},
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    isError = !userDataState.value.isValidEmail,
                    supportingText = {
                        if(!userDataState.value.isValidEmail) {
                            Text("Please Enter valid email")
                        }
                        else{Text(text = "")}
                    }
                )
            }

            CommonRow {
                OutlinedTextField(
                    value = userDataState.value.phone,
                    onValueChange = { phone ->
                        viewModel.updatePhone(phone)
                    },
                    label = {Text("Phone")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = !userDataState.value.isValidPhone,
                    supportingText = {
                        if(!userDataState.value.isValidPhone) {
                            Text("Please Enter valid phone number")
                        }
                        else{Text(text = "")}
                    }

                )
            }

            CommonRow{
                OutlinedTextField(
                    value = userDataState.value.dob,
                    onValueChange = {dob->
                        viewModel.updateDOB(dob)
                    },
                    label = { Text("DOB") },
                    isError = !userDataState.value.isValidDOB,
                    supportingText = {
                        if(!userDataState.value.isValidDOB) {
                            Text("Please Enter valid DOB")
                        }
                        else{Text(text = "")}
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = { showDateDialog = true }
                        )
                        {

                    }
                  },
                )
                Box {
                    if (showDateDialog) {
                        DatePickerDialog(
                            onDismissRequest = {},
                            {
                                TextButton(
                                    onClick = {
                                        val selectedDate = datePickerState.selectedDateMillis
                                        val instant = Instant.ofEpochMilli(selectedDate ?: 0L)
                                        val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()
                                        val formattedDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                                        viewModel.updateDOB(formattedDate)
                                        showDateDialog = false
                                    }
                                ) {
                                    Text(stringResource(R.string.ok))
                                }
                            },
                            dismissButton = {
                                TextButton({
                                    showDateDialog = false
                                }) {
                                    Text(stringResource(R.string.cancel))
                                }
                            }
                        ) {
                            DatePicker(state = datePickerState)
                        }
                    }
                }
            }

            CommonRow  {
                ElevatedButton(
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp),
                    onClick = {},
                    enabled = userDataState.value.isValid
                    ) {
                    Text(stringResource(R.string.submit))
                }
            }
        }

}

@Composable
fun CommonRow(rowContent: @Composable RowScope.()->Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
       ,content = rowContent)
}
