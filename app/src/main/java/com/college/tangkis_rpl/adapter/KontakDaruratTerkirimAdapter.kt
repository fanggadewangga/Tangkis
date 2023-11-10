package com.college.tangkis_rpl.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.college.tangkis_rpl.databinding.ItemKontakDaruratTerkirimBinding

class KontakDaruratTerkirimAdapter :
    RecyclerView.Adapter<KontakDaruratTerkirimAdapter.KontakDaruratTerkirimViewHolder>() {

    var contactNames: List<String> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): KontakDaruratTerkirimViewHolder {
        val binding =
            ItemKontakDaruratTerkirimBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KontakDaruratTerkirimViewHolder(binding)
    }

    override fun getItemCount() = contactNames.size

    override fun onBindViewHolder(holder: KontakDaruratTerkirimViewHolder, position: Int) {
        holder.bind(contactNames[position])
    }

    inner class KontakDaruratTerkirimViewHolder(private val view: ItemKontakDaruratTerkirimBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(name: String) {
            view.apply {
                tvContactName.text = name
            }
        }
    }
}