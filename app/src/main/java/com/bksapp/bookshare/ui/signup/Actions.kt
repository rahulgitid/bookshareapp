package com.bksapp.bookshare.ui.signup

import androidx.compose.runtime.Stable

@Stable
class Actions (
    val onNameChange: (String) -> Unit,
    val onEmailChange: (String) -> Unit,
    val onPhoneChange: (String) -> Unit,
    val onDobChange: (String) -> Unit,
    val onSubmit: () -> Unit,
)