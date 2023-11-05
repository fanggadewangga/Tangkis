package com.college.tangkis_rpl.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.college.tangkis_rpl.databinding.ItemContactHomeBinding
import com.college.tangkis_rpl.model.EmergencyContact

class HomeContactAdapter: RecyclerView.Adapter<HomeContactAdapter.EmergencyContactViewHolder>() {

    var contacts: List<EmergencyContact> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmergencyContactViewHolder {
        val binding = ItemContactHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmergencyContactViewHolder(binding)
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: EmergencyContactViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    inner class EmergencyContactViewHolder(private val view: ItemContactHomeBinding): RecyclerView.ViewHolder(view.root) {
        fun bind(contact: EmergencyContact) {
            view.apply {
                tvName.text = contact.name
                tvNumber.text = contact.number
            }
        }
    }
}

