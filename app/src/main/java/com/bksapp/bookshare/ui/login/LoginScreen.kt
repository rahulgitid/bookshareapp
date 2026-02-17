package com.bksapp.bookshare.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(clickLogin:()-> Unit) {


    val configuration = LocalWindowInfo.current.containerSize

    val loginViewModel: LoginViewModel = viewModel()
    val loginState = loginViewModel.loginState.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.radialGradient(
                colors = listOf(Color.Cyan,Color.Green,Color.Cyan),
                center = Offset(configuration.width/2f, configuration.height/2f),
                radius = configuration.width.toFloat(),
                tileMode = TileMode.Clamp
            ))
        .verticalScroll(scrollState)
        .imePadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    )
       {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                    horizontalArrangement = Arrangement.Center){
                    OutlinedTextField(
                        value = loginState.value.email,
                        onValueChange = {email->
                           loginViewModel.validEmail(email)
                        },
                        label = {Text("Email", color = Color.Red)},
                        maxLines = 1

                    )
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                    horizontalArrangement = Arrangement.Center){
                    OutlinedTextField(
                        value = loginState.value.password,
                        onValueChange = {password->
                            loginViewModel.validPass(password)
                        },
                        label = {Text("Password", color = Color.Red)},
                        visualTransformation = PasswordVisualTransformation(),
                        maxLines = 1
                    )
                }

                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    ElevatedButton(modifier = Modifier
                        .width(200.dp)
                        .height(50.dp),
                        enabled = loginState.value.isValid,
                        onClick = {clickLogin()},
                        contentPadding = PaddingValues(0.dp)) {
                        Box(
                            modifier = Modifier
                                .background(Brush.verticalGradient(listOf(Color.Red,Color.LightGray,Color.Black))) // Apply the gradient to the Box
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 16.dp), // Add your desired padding
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Login",
                                style = TextStyle(
                                    brush = Brush.horizontalGradient(listOf(Color.Magenta,Color.Red)),
                                    fontWeight = FontWeight.ExtraBold
                                )
                            )
                        }

                    }
                }

            }



}