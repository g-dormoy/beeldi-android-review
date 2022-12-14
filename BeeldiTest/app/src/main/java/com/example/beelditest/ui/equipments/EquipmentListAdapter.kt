package com.example.beelditest.ui.equipments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beelditest.databinding.EquipmentItemBinding
import com.example.beelditest.model.Equipment


class EquipmentListAdapter(private var list: List<Equipment>) :
    RecyclerView.Adapter<EquipmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder {
        return EquipmentViewHolder(
            EquipmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) {
        holder.bind(list[position])
    }

}


