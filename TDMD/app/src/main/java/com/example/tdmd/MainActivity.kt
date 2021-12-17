package com.example.tdmd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.ViewPager
import com.example.tdmd.Adapters.*
import com.example.tdmd.Contracts.Move
import com.example.tdmd.Contracts.Pokemon
import com.example.tdmd.Contracts.PokemonStats
import com.example.tdmd.Contracts.Type
import com.example.tdmd.Fragments.PokemonInfoAbout
import com.example.tdmd.Fragments.PokemonInfoEvo
import com.example.tdmd.Fragments.PokemonInfoMoves
import com.example.tdmd.Fragments.PokemonInfoStats
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener as OnTabSelectedListener
import android.widget.Button
import com.example.tdmd.Fragments.CollectionFragment
import com.example.tdmd.Fragments.MapFragment
import com.example.tdmd.Fragments.SettingsFragment
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    private var pokemonInfoAbout = PokemonInfoAbout();
    private var pokemonInfoEvo = PokemonInfoEvo();
    private var pokemonInfoStats = PokemonInfoStats();
    private var pokemonInfoMoves = PokemonInfoMoves();
    private var pokemonAdapter = PokemonUIAdapter(this)
    private var pokemon = Pokemon("Treecko", listOf<Type>(Type.Grass), 277, 0.5, 5.0, listOf("Overgrow", "Unburden"), PokemonStats(40,45,35,65,55,70), listOf(Pokemon("Grovyle", listOf(Type.Grass), "placeholder"), Pokemon("Sceptile", listOf(Type.Grass), "placeholder")), listOf(Move("Pound", Type.Normal, 40), Move("Mega Drain", Type.Grass, 40)), "placeholder")


    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        hideSystemBars()

        mainInfoTabsFragmentsInit()
    }

        //val recyclerAdapter = RecyclerAdapter(this, "Pikachu", "Electric type pokemon", 11, "HOI")

    private fun hideSystemBars() {
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return
        // Configure the behavior of the hidden system bars
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }

    private fun pokemonInfoTabsFragmentsInit() {
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val tabAbout = findViewById<TabItem>(R.id.aboutTab)
        val tabStats = findViewById<TabItem>(R.id.statsTab)
        val tabEvolution = findViewById<TabItem>(R.id.evoTab)
        val tabMoves = findViewById<TabItem>(R.id.movesTab)

        val viewPager = findViewById<ViewPager>(R.id.PokemonViewPager)

        val pagerAdapter = PokemonInfoPagerAdapter(
            getSupportFragmentManager(),
            tabLayout.tabCount,
            pokemonAdapter
        )
        pagerAdapter.setCurrentPokemon(pokemon)

        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager.setCurrentItem(tab.position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun mainInfoTabsFragmentsInit() {
        val tabLayout = findViewById<TabLayout>(R.id.MainTabLayout)
        val tabAbout = findViewById<TabItem>(R.id.tabMap)
        val tabStats = findViewById<TabItem>(R.id.tabCollection)
        val tabEvolution = findViewById<TabItem>(R.id.tabSettings)

        val viewPager = findViewById<ViewPager>(R.id.MainViewPager)

        val pagerAdapter = MainPagerAdapter(
            getSupportFragmentManager(),
            tabLayout.tabCount,
        )

        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager.setCurrentItem(tab.position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}