package com.example.beelditest

import com.example.beelditest.datasource.EquipmentDataSource
import com.example.beelditest.datasource.EquipmentDataSourceImpl
import com.example.beelditest.repository.EquipmentRepository
import com.example.beelditest.repository.EquipmentRepositoryImpl
import com.example.beelditest.ui.equipments.EquipmentsViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val di = module {

    //FirebaseDB
    single { Firebase.database }

    // DataSource
    single<EquipmentDataSource> { EquipmentDataSourceImpl(get()) }

    // Repository
    single<EquipmentRepository> { EquipmentRepositoryImpl(equipmentDataSource = get()) }

    //viewModel
    viewModel {
        EquipmentsViewModel(repository = get())
    }
}
