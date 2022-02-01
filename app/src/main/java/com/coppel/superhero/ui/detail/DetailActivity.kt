package com.coppel.superhero.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.coppel.superhero.MyApplication
import com.coppel.superhero.R
import com.coppel.superhero.core.domain.entities.HeroItem
import com.coppel.superhero.databinding.ActivityDetailBinding
import com.coppel.superhero.ui.viewmodel.MainViewModel
import com.coppel.superhero.ui.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: MainViewModel
    private var isFavorite = false

    companion object {
        const val EXTRA_HERO = "hero"
    }

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        initView()
    }

    private fun initView() {
        binding.toolbar.imgBack.visibility = View.VISIBLE
        binding.toolbar.imgBack.setOnClickListener {
            onBackPressed()
        }

        val extraHero = intent.getParcelableExtra<HeroItem>(EXTRA_HERO)
        val image = extraHero?.image.toString()
        val title = extraHero?.name.toString()
        val desc = extraHero?.desc.toString()
        val strength = extraHero?.strength.toString()
        val durability = extraHero?.durability.toString()
        val combat = extraHero?.combat.toString()
        val power = extraHero?.power.toString()
        val speed = extraHero?.speed.toString()
        val intelligence = extraHero?.intelligence.toString()

        binding.toolbar.tvTitle.text = title
        Glide.with(this)
            .load(image)
            .apply(RequestOptions().override(500, 500))
            .into(binding.imgSuperhero)
        binding.tvName.text = "Name: ${title}"
        binding.tvStrength.text = "Strength: ${strength}"
        binding.tvDurability.text = "Durability: ${durability}"
        binding.tvCombat.text = "Combat: ${combat}"
        binding.tvPower.text = "Power: ${power}"
        binding.tvSpeed.text = "Speed: ${speed}"
        binding.tvIntelligence.text = "Intelligence: ${intelligence}"

        initFavorite(title)

        binding.imgFavorite.setOnClickListener {
            if (isFavorite) {
                Thread {
                    viewModel.deleteFavoriteHero(title.toString())
                }.start()
                binding.imgFavorite.setImageResource(R.drawable.ic_favorite_border)
                isFavorite = false
                Toast.makeText(this, "${title} is removed from favorites!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Thread {
                    viewModel.insertFavoriteHero(
                        HeroItem(
                            image,
                            title,
                            desc,
                            strength,
                            durability,
                            combat,
                            power,
                            speed,
                            intelligence
                        )
                    )
                }.start()
                binding.imgFavorite.setImageResource(R.drawable.ic_favorite)
                isFavorite = true
                Toast.makeText(this, "${title} is added to favorites!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initFavorite(title: String?) {
        viewModel.getFavoriteHeroes().observe(this) { heroes ->
            val listHeroes = heroes.heroItems
            if (listHeroes != null) {
                for (hero in listHeroes) {
                    if (title == hero.name) {
                        binding.imgFavorite.setImageResource(R.drawable.ic_favorite)
                        isFavorite = true
                    }
                }
            }
        }
    }
}