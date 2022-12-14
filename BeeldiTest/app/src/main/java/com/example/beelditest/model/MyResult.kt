package com.example.beelditest.model

sealed class MyResultResult<out Success, out Failure : Throwable>

data class Success<out Success>(val value: Success) : MyResultResult<Success, Nothing>()
data class Failure<out Failure : Throwable>(val reason: Failure) : MyResultResult<Nothing, Failure>()
