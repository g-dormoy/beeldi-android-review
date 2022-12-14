package com.example.beelditest.ui.equipments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beelditest.model.Equipment
import com.example.beelditest.model.Failure
import com.example.beelditest.model.NetworkException
import com.example.beelditest.model.Success
import com.example.beelditest.repository.EquipmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EquipmentsViewModel(
    private val repository: EquipmentRepository) : ViewModel() {

    private val currentEquipmentList = MutableLiveData<List<Equipment?>>()

    val equipments = MutableLiveData<List<Equipment?>>()
    val error = MutableLiveData(false)
    val errorMessage = MutableLiveData("")

    init {
        getEquipmentsList()
    }

    fun getEquipmentsList() {
        error.value = false
        viewModelScope.launch(Dispatchers.IO) {
                when (val response = repository.getEquipments()) {
                    is Failure -> {
                        withContext(Dispatchers.Main) {
                            error.value = true
                            errorMessage.value =
                                if (response.reason is NetworkException) "Veuillez vérifier votre connexion et réessayez"
                                else "Un problème technique est survenu, réessayez ultérieurement"
                        }
                    }
                    is Success -> {
                        withContext(Dispatchers.Main) {
                            error.value = false
                            currentEquipmentList.value = response.value.orEmpty()
                            equipments.value = currentEquipmentList.value
                        }
                    }
                }
            }
    }
}
