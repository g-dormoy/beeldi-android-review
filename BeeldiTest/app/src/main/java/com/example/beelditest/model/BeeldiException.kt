package com.example.beelditest.model

sealed class BeeldiException : Exception()


data class NetworkException(override val message: String = DEFAULT_MESSAGE) :
    BeeldiException() {
    companion object {
        const val DEFAULT_MESSAGE = "Client is offline"
    }
}

data class TechnicalException(
    override val message: String? = DEFAULT_MESSAGE,
    override val cause: Throwable?
) :
    BeeldiException() {
    companion object {
        const val DEFAULT_MESSAGE = "An unknown problem occurred"
    }
}
