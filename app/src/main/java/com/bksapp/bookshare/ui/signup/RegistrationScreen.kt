package com.bksapp.bookshare.ui.signup

import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
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
import com.bksapp.bookshare.R
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SignUpScreen() {

    var emailState by remember { mutableStateOf("") }
    var nameState by remember { mutableStateOf("") }
    var phoneState by remember { mutableStateOf("") }
    var dobState by remember { mutableStateOf("") }
    var showDateDialog by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Input
    )

    val backColor = Brush.verticalGradient(
        listOf(Color.Red,Color.LightGray,Color.LightGray,Color.Green)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
            .background(brush = backColor),

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                OutlinedTextField(
                    value = nameState,
                    onValueChange = { name ->
                        nameState = name
                    },
                    label = { Text("name") }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                OutlinedTextField(
                    value = emailState,
                    onValueChange = { email ->
                        emailState = email
                    },
                    label = {Text("Email")},
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                OutlinedTextField(
                    value = phoneState,
                    onValueChange = { phone ->
                        phoneState = phone
                    },
                    label = {Text("Phone")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            {
                OutlinedTextField(
                    value = dobState,
                    onValueChange = {},
                    label = { Text("DOB") },
                    trailingIcon = {
                        IconButton(
                            onClick = { showDateDialog = true }
                        )
                        {

                    }
                  },
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
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
                                        dobState = formattedDate
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

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ElevatedButton(
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp),
                    onClick = {}
                ) {
                    Text(stringResource(R.string.submit))
                }
            }
        }
    }
}
