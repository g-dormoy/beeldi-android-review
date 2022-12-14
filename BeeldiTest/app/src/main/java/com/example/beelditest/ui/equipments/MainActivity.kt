package com.example.beelditest.ui.equipments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.beelditest.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val equipmentViewModel: EquipmentsViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        equipmentViewModel.equipments.observe(this) {
            val adapter = EquipmentListAdapter(it.filterNotNull())
            binding.equipmentsRecyclerView.adapter = adapter
        }

        equipmentViewModel.error.observe(this) {
            binding.technicalError.isVisible = it
        }

        equipmentViewModel.errorMessage.observe(this) {
            binding.message.text = it
        }

        binding.retry.setOnClickListener { equipmentViewModel.getEquipmentsList() }
    }
}