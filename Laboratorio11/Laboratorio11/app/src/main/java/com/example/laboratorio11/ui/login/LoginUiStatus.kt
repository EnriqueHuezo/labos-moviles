package com.example.laboratorio11.ui.login

import java.lang.Exception

sealed class LoginUiStatus {
    object Resume : LoginUiStatus()
    class Error(val exception: Exception) : LoginUiStatus()
    data class ErrorWithMessage(val message: String) : LoginUiStatus()
    class Success(val token: String) : LoginUiStatus()
}