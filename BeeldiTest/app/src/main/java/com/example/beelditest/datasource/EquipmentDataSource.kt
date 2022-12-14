package com.example.beelditest.datasource

import com.example.beelditest.model.Failure
import com.example.beelditest.model.MyResultResult
import com.example.beelditest.model.Success
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase

interface EquipmentDataSource {
    suspend fun getEquipmentsList(): MyResultResult<DataSnapshot, Exception>
}

class EquipmentDataSourceImpl(private val firebaseDatabase: FirebaseDatabase) :
    EquipmentDataSource {

    private val firebaseDbReference = firebaseDatabase.getReference("Equipments")

    override suspend fun getEquipmentsList(): MyResultResult<DataSnapshot, Exception> {
        val response = firebaseDbReference.get()
        return when (response.isSuccessful) {
            true -> Success(response.result)
            else -> Failure(response.exception!!)
        }
    }
}