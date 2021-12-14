package com.example.tdmd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tdmd.Fragments.CollectionFragment
import com.example.tdmd.Fragments.MapFragment
import com.example.tdmd.Fragments.SettingsFragment
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentInit()
    }

        //val recyclerAdapter = RecyclerAdapter(this, "Pikachu", "Electric type pokemon", 11, "HOI")

    private fun fragmentInit() {
        val mapFragment = MapFragment();
        val colllectionFragment = CollectionFragment();
        val settingsFragment = SettingsFragment();

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, colllectionFragment)
            addToBackStack(null)
            commit();
        }

        findViewById<Button>(R.id.buttonMap).setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, mapFragment)
                addToBackStack(null)
                commit();
            }
        }

        findViewById<Button>(R.id.buttonCollection).setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, colllectionFragment)
                addToBackStack(null)
                commit();
            }
        }

        findViewById<Button>(R.id.buttonSettings).setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, settingsFragment)
                addToBackStack(null)
                commit();
            }
        }
    }
}