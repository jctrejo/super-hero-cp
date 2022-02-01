package com.coppel.superhero.favorites.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.coppel.superhero.MyApplication
import com.coppel.superhero.favorites.DaggerFavoriteComponent
import com.coppel.superhero.favorites.databinding.ActivityFavoriteBinding
import com.coppel.superhero.ui.adapter.GridSuperHeroAdapter
import com.coppel.superhero.ui.viewmodel.MainViewModel
import com.coppel.superhero.ui.viewmodel.ViewModelFactory
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    companion object {
        const val TITLE = "Favorites"
    }
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        val coreComponent = (application as MyApplication).coreComponent
        DaggerFavoriteComponent.factory().create(coreComponent).inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,factory)[MainViewModel::class.java]

        initView()
    }

    override fun onStart() {
        super.onStart()
        loadFavoritesData()
    }

    private fun loadFavoritesData() {
        viewModel.getFavoriteHeroes().observe(this) { favorites ->
            val adapter = GridSuperHeroAdapter(favorites.heroItems!!)
            binding.rvFavorites.adapter = adapter
            binding.rvFavorites.layoutManager = GridLayoutManager(applicationContext, 2)
        }
    }

    private fun initView() {
        binding.toolbar.tvTitle.text = TITLE
        binding.toolbar.imgBack.visibility = View.VISIBLE
        binding.toolbar.imgBack.setOnClickListener {
            onBackPressed()
        }
    }
}
