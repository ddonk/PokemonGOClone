package com.example.tdmd

import android.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = FirstFragment();
        val secondFragment = SecondFragment();

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, firstFragment)
            commit();
        }

        findViewById<Button>(R.id.buttonFrag1).setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, firstFragment)
                addToBackStack(null)
                commit();
            }
        }

        findViewById<Button>(R.id.buttonFrag2).setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, secondFragment)
                addToBackStack(null)
                commit();
            }
        }
    }
}