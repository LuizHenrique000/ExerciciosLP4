package com.lp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val image = findViewById<ImageView>(R.id.image);
        Glide
            .with(this)
            .load("https://static.roland.com/assets/images/products/gallery/v-piano_grand_angle_open_full_gal.jpg")
            .into(image)
    }
}