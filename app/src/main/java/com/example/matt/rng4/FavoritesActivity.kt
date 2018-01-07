package com.example.matt.rng4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_favorites.*

class FavoritesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        val db = DatabaseHandler(this)

        val data = db.readData()
        textFavorites.text = ""
        for (i in 0..(data.size - 1)){
            textFavorites.append(data.get(i).name + "\n")
        }

    }
}
