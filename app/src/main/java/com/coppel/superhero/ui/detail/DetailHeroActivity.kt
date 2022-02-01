package com.coppel.superhero.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.coppel.superhero.MyApplication
import com.coppel.superhero.core.domain.entities.ImageItem
import com.coppel.superhero.databinding.ActivityDetailHeroBinding
import com.coppel.superhero.ui.viewmodel.MainViewModel
import com.coppel.superhero.ui.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import javax.inject.Inject

class DetailHeroActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_HERO_HOME = "hero_home"
    }

    private lateinit var binding: ActivityDetailHeroBinding
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        initView()
    }

    private fun initView() {
        val extraHero = intent.getParcelableExtra<ImageItem>(EXTRA_HERO_HOME)

        Glide.with(this).load(extraHero?.url)
            .apply(RequestOptions())
            .into(binding.coverPageImageView)

        viewModel.getBiographyHeroes(extraHero?.id ?: "").observe(this) { results ->
            binding.movirTitleTextView.text = results.fullName
        }

        viewModel.getWork(extraHero?.id ?: "").observe(this) { results ->
            binding.movieDescriptionTextView13.text = results.occupation
        }
    }
}
