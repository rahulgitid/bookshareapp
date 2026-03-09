package com.bksapp.bookshare.ui.signup

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksapp.bookshare.R
import com.bksapp.bookshare.utils.DatePickerModal


@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SignUpScreen(navigateTo:()-> Unit) {
    val scrollState = rememberScrollState()
    val viewModel = hiltViewModel<SignupViewModel>()
    val userDataState  by viewModel.signupState.collectAsStateWithLifecycle()
    val signupActions = remember(viewModel) {
        Actions(
           onNameChange = viewModel::updateName,
           onEmailChange = viewModel::updateEmail,
           onPhoneChange = viewModel::updatePhone,
           onDobChange = viewModel::updateDOB,
           onSubmit = navigateTo,
       ) }

    var showDateDialog by remember { mutableStateOf(false ) }

        Column(modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .imePadding()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
                SignupTextField(
                    userDataState.name,
                    signupActions.onNameChange,
                    "name",
                    !userDataState.isValidName,
                    "Please Enter valid name",
                    KeyboardOptions(keyboardType = KeyboardType.Text),
                )
                SignupTextField(
                    userDataState.email,
                    signupActions.onEmailChange,
                    "Email",
                    !userDataState.isValidName,
                    "Please Enter valid email",
                    KeyboardOptions(keyboardType = KeyboardType.Email),
                )
                SignupTextField(
                    userDataState.phone,
                    signupActions.onPhoneChange,
                    "Phone",
                    !userDataState.isValidPhone,
                    "Please Enter valid Phone Number",
                    KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                SignupTextField(
                    userDataState.dob,
                    signupActions.onDobChange,
                    "DOB",
                    !userDataState.isValidDOB,
                    "Please Enter valid Phone DOB",
                     trailingIcon = {
                         IconButton(onClick = { showDateDialog = true })
                         {
                             Icon(Icons.Filled.DateRange, contentDescription = "")
                         }
                     },
                    enable = false
                )
                Row(modifier = Modifier.fillMaxWidth().padding(10.dp),
              horizontalArrangement = Arrangement.Center) {
              ElevatedButton(
                  modifier = Modifier
                      .width(200.dp)
                      .height(50.dp),
                  onClick =  signupActions.onSubmit ,
                  enabled = userDataState.isValid
              ) {
                  Text(stringResource(R.string.submit))
              }
          }
        }


     if(showDateDialog) {
        DatePickerModal({ dateString ->
            viewModel.updateDOB(dateString)
        }, {
            showDateDialog = false
        })
    }

}


@Composable
fun SignupTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    errorText: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    enable : Boolean = true
) {

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            isError = isError,
            keyboardOptions = keyboardOptions,
            trailingIcon = trailingIcon,
            supportingText = {
                if (isError) Text(errorText) else Text("")
            },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            enabled = enable
        )

}


