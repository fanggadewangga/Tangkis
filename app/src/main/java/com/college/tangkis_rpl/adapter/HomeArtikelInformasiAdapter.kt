package com.college.tangkis_rpl.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.college.tangkis_rpl.databinding.ItemArticleHomeBinding
import com.college.tangkis_rpl.model.ArtikelInformasi

class HomeArtikelInformasiAdapter : RecyclerView.Adapter<HomeArtikelInformasiAdapter.HomeArticleViewHolder>() {

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
                tvTitle.text = artikelInformasi.title
                tvContent.text = artikelInformasi.content
            }
            itemView.let {
                Glide.with(it.context)
                    .load(artikelInformasi.imageUrl)
                    .apply(RequestOptions.centerCropTransform())
                    .into(view.ivArticle)
            }
        }
    }
}