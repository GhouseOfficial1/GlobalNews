package com.exa.globalnews.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exa.globalnews.R
import com.exa.globalnews.databinding.ItemNewsBinding
import com.exa.globalnews.models.Article

class NewsAdapter(
    private val adapterEventListener: AdapterEventListener
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsArticles: MutableList<Article> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return newsArticles.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = newsArticles[position]
        holder.itemNewsLayoutBinding.apply {
            Glide.with(ivPosterArticle).load(article.urlToImage).into(ivPosterArticle)
            tvTitleArticle.text = article.title
            tvDescArticle.text = article.description
            tvSourceArticle.text = article.source?.name
            if (article.isBookmark) {
                ivBookmarkArticle.setImageResource(R.drawable.ic_bookmarked_article)
                root.setOnClickListener {
                    adapterEventListener.onNewsClickListener(
                        article
                    )
                }
                ivBookmarkArticle.setOnClickListener {
                    ivBookmarkArticle.setImageResource(R.drawable.ic_bookmark_article)
                    adapterEventListener.onNewsLikeListener(position, article)
                }
            } else {
                root.setOnClickListener {
                    adapterEventListener.onNewsClickListener(
                        article
                    )
                }
                ivBookmarkArticle.setOnClickListener {
                    ivBookmarkArticle.setImageResource(R.drawable.ic_bookmarked_article)
                    adapterEventListener.onNewsLikeListener(position, article)
                }
            }
        }
    }

    fun setArticles(articles: List<Article>) {
        newsArticles = articles.toMutableList()
        notifyDataSetChanged()
    }

    fun removeArticle(position: Int) {
        if (position in newsArticles.indices) {
            newsArticles.removeAt(position)
            notifyItemRemoved(position)
        }
    }


    inner class NewsViewHolder(val itemNewsLayoutBinding: ItemNewsBinding) :
        RecyclerView.ViewHolder(itemNewsLayoutBinding.root) {
    }
}