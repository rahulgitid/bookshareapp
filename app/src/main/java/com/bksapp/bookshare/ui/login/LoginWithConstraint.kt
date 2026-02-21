package com.bksapp.bookshare.ui.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksapp.bookshare.data.repository.NetworkStatus


@Composable
fun LoginScreen(onLogin:()->Unit,onSignup:()->Unit) {

    val context = LocalContext.current
    val configuration = LocalWindowInfo.current.containerSize

    val loginViewModel = hiltViewModel<LoginViewModel>()
    val loginState = loginViewModel.loginState.collectAsStateWithLifecycle()
    val loginStatus = loginViewModel.loginStatus.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(loginStatus.value) {
        when (loginStatus.value) {
            NetworkStatus.Idle -> {
                Log.i("NetworkStatus ", "IDEL")
            }

            NetworkStatus.Loading -> {
                Log.i("NetworkStatus ", "LOADING")
            }

            is NetworkStatus.Success -> {
                onLogin()
                Log.i("NetworkStatus ", "SUCCESS")
            }

            is NetworkStatus.Error -> {
                Log.i("NetworkStatus ", "ERROR")
                Toast.makeText(
                    context,
                    "" + (loginStatus.value as NetworkStatus.Error).error,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.radialGradient(
                colors = listOf(Color.Cyan, Color.Green, Color.Cyan),
                center = Offset(configuration.width / 2f, configuration.height / 2f),
                radius = configuration.width.toFloat(),
                tileMode = TileMode.Clamp
            )
        )
        .imePadding()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize().padding(16.dp)
        )
        {

            val (emailField, passField, submitBT, signupTxt) = createRefs()

            OutlinedTextField(
                value = loginState.value.email,
                onValueChange = { email ->
                    loginViewModel.validEmail(email)
                },
                label = { Text("Email", color = Color.Red) },
                maxLines = 1,
                modifier = Modifier
                    .constrainAs(emailField) {
                        bottom.linkTo(passField.top)
                        start.linkTo(passField.start)
                        end.linkTo(passField.end)
                    }

            )
            OutlinedTextField(
                value = loginState.value.password,
                onValueChange = { password ->
                    loginViewModel.validPass(password)
                },
                label = { Text("Password", color = Color.Red) },
                visualTransformation = PasswordVisualTransformation(),
                maxLines = 1,
                modifier = Modifier
                    .constrainAs(passField) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            ElevatedButton(
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
                    .constrainAs(submitBT) {
                        bottom.linkTo(signupTxt.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                enabled = loginState.value.isValid,
                onClick = {
                           keyboardController?.hide()
                           loginViewModel.callLogin()
                          },
                contentPadding = PaddingValues(0.dp)
            ) {
                    Text(
                        "Login",
                        style = TextStyle(
                            brush = Brush.horizontalGradient(listOf(Color.Magenta, Color.Red)),
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

            }
            Text(
                modifier = Modifier
                    .clickable(true, onClick = { onSignup() })
                    .constrainAs(signupTxt) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(top = 16.dp),
                text = "Register",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        }
        if (loginStatus.value == NetworkStatus.Loading)
        {
            Column(modifier = Modifier
                .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                CircularProgressIndicator(
                    color = Color.Red,
                    strokeWidth = 10.dp
                )

            }
        }
    }
}