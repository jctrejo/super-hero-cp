package com.coppel.superhero.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.coppel.superhero.MyApplication
import com.coppel.superhero.core.domain.entities.*
import com.coppel.superhero.databinding.ActivityMainBinding
import com.coppel.superhero.ui.adapter.GridSuperHeroAdapter
import com.coppel.superhero.ui.viewmodel.MainViewModel
import com.coppel.superhero.ui.viewmodel.ViewModelFactory
import com.coppel.superhero.ui.adapter.ListHeroAdapter
import com.jakewharton.rxbinding2.widget.RxTextView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        const val TITLE = "SuperHero"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    val newImageItem: ArrayList<ImageItem> = arrayListOf()

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,factory)[MainViewModel::class.java]
        binding.recyclerView.layoutManager = GridLayoutManager(applicationContext,3)
        binding.listHerorecyclerView.layoutManager = GridLayoutManager(applicationContext,3)

        initView()
    }

    private fun initView(){
        setupListHero()
        binding.toolbar.tvTitle.text = TITLE
        binding.toolbar.imgFavoriteToolbar.visibility = View.VISIBLE
        binding.toolbar.imgFavoriteToolbar.setOnClickListener {
            val uri = Uri.parse("superhero://favorites")
            val intent = Intent(Intent.ACTION_VIEW,uri)
            startActivity(intent)
        }
        val searchStream = RxTextView.textChanges(binding.searchBar)
        searchStream.subscribe {
            loadAPIData()
        }
    }

    private fun setupListHero() {
        val newBiographyItem: MutableList<BiographyItem> = mutableListOf()
        val newPowerstatsItem: MutableList<PowerstatsItem> = mutableListOf()
        val newAppearanceItem: MutableList<AppearanceItem> = mutableListOf()
        val newWorkItemItem: MutableList<WorkItem> = mutableListOf()
        val newConnectionsItem: MutableList<ConnectionsItem> = mutableListOf()
        val newListHeroItem: MutableList<ListHeroItem> = mutableListOf()

        viewModel.getImage("1").observe(this@MainActivity) { results ->
            setupModel(results.id.toInt() +1)
        }
        // TODO: Use of coroutines
/*
        lifecycleScope.launch {
            (1..713).map {
                withContext(Dispatchers.Main) {
                    viewModel.getImage(it.toString()).observe(this@MainActivity) { results ->
                        newImageItem.add(results)
                    }
                    conuta++
                }
            }
        }
*/
        // TODO: execution of more services separately
        /*
        viewModel.getBiographyHeroes(i.toString()).observe(this) { results ->
             newBiographyItem.add(results)
         }

         viewModel.getPowerstats(i.toString()).observe(this) { results ->
             newPowerstatsItem.add(results)
         }

         viewModel.getAppearance(i.toString()).observe(this) { results ->
             newAppearanceItem.add(results)
         }

         viewModel.getWork(i.toString()).observe(this) { results ->
             newWorkItemItem.add(results)
         }

         viewModel.getConnections(i.toString()).observe(this) { results ->
             newConnectionsItem.add(results)
         }

         viewModel.getImage(i.toString()).observe(this) { results ->
             newImageItem.add(results)
         }*/

        val adapter = ListHeroAdapter(newImageItem, false)
        binding.listHerorecyclerView.adapter = adapter
    }

    private fun setupModel(value: Int) {
        viewModel.getImage(value.toString()).observe(this@MainActivity) { results ->
            newImageItem.add(results)
        }
    }

    private fun loadAPIData(){
        val searchQuery = binding.searchBar.text.toString()
        viewModel.getHeroes(searchQuery).observe(this) { results ->
            if (!results.isError) {
                binding.recyclerView.visibility = View.VISIBLE
                val adapter = GridSuperHeroAdapter(results.heroItems!!)
                binding.recyclerView.adapter = adapter
            } else {
                binding.recyclerView.visibility = View.GONE
            }
        }

        viewModel.getLoadingStatus().observe(this) { loading ->
            if (loading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}
