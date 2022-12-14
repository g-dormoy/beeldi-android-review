package com.example.beelditest.ui.equipments

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beelditest.R
import com.example.beelditest.databinding.EquipmentItemBinding
import com.example.beelditest.model.Equipment

class EquipmentViewHolder(private val binding: EquipmentItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Equipment) {
        binding.domainMaterialTextView.text = item.domain
        binding.nameMaterialTextView.text = item.name
        binding.faultMaterialTextView.text = if (item.nbFaults > 1) itemView.context.getString(
            R.string.equipment_fault_number_plural,
            item.nbFaults
        )
        else itemView.context.getString(R.string.equipment_fault_number, item.nbFaults)

        Glide.with(itemView.context)
            .load(item.photo)
            .error(R.drawable.header_placeholder)
            .placeholder(R.drawable.header_placeholder)
            .into(binding.pictureShapeableImageView)
    }
}