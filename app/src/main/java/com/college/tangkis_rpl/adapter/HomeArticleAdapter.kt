package com.college.tangkis_rpl.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.college.tangkis_rpl.databinding.ItemArticleHomeBinding
import com.college.tangkis_rpl.model.Article

class HomeArticleAdapter : RecyclerView.Adapter<HomeArticleAdapter.HomeArticleViewHolder>() {

    var articles: List<Article> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeArticleViewHolder {
        val binding = ItemArticleHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeArticleViewHolder(binding)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: HomeArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    inner class HomeArticleViewHolder(private val view: ItemArticleHomeBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(article: Article) {
            view.apply {
                tvTitle.text = article.title
                tvContent.text = article.content
            }
            itemView.let {
                Glide.with(it.context)
                    .load(article.imageUrl)
                    .apply(RequestOptions.centerCropTransform())
                    .into(view.ivArticle)
            }
        }
    }
}