package com.college.tangkis_rpl.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.college.tangkis_rpl.databinding.ItemContactBinding
import com.college.tangkis_rpl.model.KontakPerangkat

class DaftarKontakPerangkatAdapter(private val pilihKontak:(KontakPerangkat) -> Unit)  : RecyclerView.Adapter<DaftarKontakPerangkatAdapter.KontakPerangkatViewHolder>() {

    var daftarKontakPerangkat : List<KontakPerangkat> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KontakPerangkatViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KontakPerangkatViewHolder(binding)
    }

    override fun getItemCount() = daftarKontakPerangkat.size

    override fun onBindViewHolder(holder: KontakPerangkatViewHolder, position: Int) {
        holder.bind(daftarKontakPerangkat[position])
    }

    inner class KontakPerangkatViewHolder(private val view: ItemContactBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(kontakPerangkat: KontakPerangkat) {
            view.apply {
                tvName.text = kontakPerangkat.getNama()
                tvNumber.text = kontakPerangkat.getNomor()
                ivDelete.visibility = View.GONE
            }
            itemView.setOnClickListener { pilihKontak.invoke(kontakPerangkat) }
        }
    }
}