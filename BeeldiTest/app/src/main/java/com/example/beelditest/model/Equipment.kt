package com.example.beelditest.model


data class Equipment(
    var id: String,
    val nbFaults: Int,
    val notes: String,
    val serialNumber: String,
    val photo: String,
    val niveau: String,
    val building: String,
    val local: String,
    val domain: String,
    val name: String,
    val model: String,
    val brand: String,
    val status: String
) {
    constructor() : this(
        "",
        0, "", "",
        "", "", "", "", "", "", "", "", ""
    )
}
