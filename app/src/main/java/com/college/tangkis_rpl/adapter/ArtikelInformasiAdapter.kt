package com.college.tangkis_rpl.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.college.tangkis_rpl.databinding.ItemArticleHomeBinding
import com.college.tangkis_rpl.model.ArtikelInformasi

class ArtikelInformasiAdapter(private val onClick: (String) -> Unit) : RecyclerView.Adapter<ArtikelInformasiAdapter.HomeArticleViewHolder>() {

    var artikelInformasis: List<ArtikelInformasi> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeArticleViewHolder {
        val binding = ItemArticleHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeArticleViewHolder(binding)
    }

    override fun getItemCount() = artikelInformasis.size

    override fun onBindViewHolder(holder: HomeArticleViewHolder, position: Int) {
        holder.bind(artikelInformasis[position])
    }

    inner class HomeArticleViewHolder(private val view: ItemArticleHomeBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(artikelInformasi: ArtikelInformasi) {
            view.apply {
                tvTitle.text = artikelInformasi.getJudul()
                tvContent.text = artikelInformasi.getKonten()
            }
            itemView.let {
                Glide.with(it.context)
                    .load(artikelInformasi.getImage())
                    .apply(RequestOptions.centerCropTransform())
                    .into(view.ivArticle)
            }
            itemView.setOnClickListener {
                onClick.invoke(artikelInformasi.getIdArtikel())
            }
        }
    }
}