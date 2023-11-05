package com.college.tangkis_rpl.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.college.tangkis_rpl.databinding.ItemContactBinding
import com.college.tangkis_rpl.model.KontakDarurat

class KontakDaruratAdapter(private val onDeleteClick: () -> Unit) : RecyclerView.Adapter<KontakDaruratAdapter.ContactViewHolder>() {

    var contacts : List<KontakDarurat> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    inner class ContactViewHolder(private val view: ItemContactBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(contact: KontakDarurat) {
            view.apply {
                tvName.text = contact.name
                tvNumber.text = contact.number
                ivDelete.setOnClickListener {
                    onDeleteClick.invoke()
                }
            }
        }
    }
}
