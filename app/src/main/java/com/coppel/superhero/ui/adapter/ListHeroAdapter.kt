package com.coppel.superhero.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coppel.superhero.core.domain.entities.ImageItem
import com.coppel.superhero.databinding.ItemSuperheroBinding
import com.coppel.superhero.ui.detail.DetailActivity
import com.coppel.superhero.ui.detail.DetailHeroActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListHeroAdapter(val listSuperHero: ArrayList<ImageItem>, isDetail: Boolean): RecyclerView.Adapter<ListHeroAdapter.ListHeroViewHolder>() {

    val isDetail = isDetail

    inner class ListHeroViewHolder(private val binding: ItemSuperheroBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(superhero: ImageItem){
            with(binding){
                Glide.with(itemView.context)
                    .load(superhero.url)
                    .apply(RequestOptions().override(500, 500))
                    .into(image)
                title.text = superhero.name

                if (isDetail) {
                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.EXTRA_HERO, superhero)
                        itemView.context.startActivity(intent)
                    }
                } else {
                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailHeroActivity::class.java)
                        intent.putExtra(DetailHeroActivity.EXTRA_HERO_HOME, superhero)
                        itemView.context.startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHeroViewHolder {
        val binding = ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListHeroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListHeroViewHolder, position: Int) {
        holder.bind(listSuperHero[position])
    }

    override fun getItemCount(): Int {
        return listSuperHero.size
    }
}
