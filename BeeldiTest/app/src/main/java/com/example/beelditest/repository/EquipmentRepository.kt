package com.example.beelditest.repository

import com.example.beelditest.datasource.EquipmentDataSource
import com.example.beelditest.model.*
import timber.log.Timber

interface EquipmentRepository {
    suspend fun getEquipments(): MyResultResult<List<Equipment?>?, Exception>
}

const val NETWORKEXCEPTION = "Client is offline"

class EquipmentRepositoryImpl(private val equipmentDataSource: EquipmentDataSource) :
    EquipmentRepository {

    override suspend fun getEquipments(): MyResultResult<List<Equipment?>?, Exception> {
        return when (val response = equipmentDataSource.getEquipmentsList()) {
            is Success -> {
                Timber.d("RESPONSE: $response")
                val equipments = ArrayList<Equipment?>()
                for (userSnapshot in response.value.children) {
                    val userModel = userSnapshot.getValue(Equipment::class.java)
                    userModel?.id = userSnapshot.key.orEmpty()
                    equipments.add(userModel)
                }
                Success(equipments)
            }
            is Failure -> {
                Timber.e(response.reason)

                if (response.reason.message.equals(NETWORKEXCEPTION)) Failure(
                    NetworkException()
                )
                else Failure(TechnicalException(response.reason.message, response.reason))
            }
        }
    }

}